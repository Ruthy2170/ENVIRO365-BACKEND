package com.enviro.assessment.junior.moipone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.repository.InvestmentRepository;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    public Investment getInvestmentById(Long id) {
        return investmentRepository.findById(id).orElse(null);

    }

    public List<Investment> getInvestmentsByInvestorId(String investorId) {
        return investmentRepository.findByInvestorId(investorId);
    }

    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }
}
