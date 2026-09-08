package com.enviro.assessment.junior.moipone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.service.InvestmentService;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.getAllInvestments();
    }

    @GetMapping("/{id}")
    public Investment getInvestmentById(@PathVariable Long id) {
        return investmentService.getInvestmentById(id);
    }
}
