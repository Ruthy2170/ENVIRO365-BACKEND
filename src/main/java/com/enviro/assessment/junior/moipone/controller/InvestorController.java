package com.enviro.assessment.junior.moipone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.entity.Investor;
import com.enviro.assessment.junior.moipone.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.moipone.service.InvestmentService;
import com.enviro.assessment.junior.moipone.service.InvestorService;

@RestController
@RequestMapping("/api/investors")
public class InvestorController {

    private final InvestorService investorService;
    private final InvestmentService investmentService;

    public InvestorController(
            InvestorService investorService,
            InvestmentService investmentService) {

        this.investorService = investorService;
        this.investmentService = investmentService;
    }

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{investorId}/portfolio")
    public Map<String, Object> getInvestorPortfolio(
            @PathVariable String investorId) {

        Investor investor
                = investorService.getInvestorByInvestorId(investorId);

        if (investor == null) {
            throw new ResourceNotFoundException(
                    "Investor not found: " + investorId
            );
        }

        List<Investment> investments
                = investmentService.getInvestmentsByInvestorId(investorId);

        Map<String, Object> portfolio = new java.util.HashMap<>();

        portfolio.put("investor", investor);
        portfolio.put("products", investments);

        return portfolio;
    }
}
