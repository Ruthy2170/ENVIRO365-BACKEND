package com.enviro.assessment.junior.moipone.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.moipone.repository.InvestmentRepository;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Page<Investment> getAllInvestments(Pageable pageable) {
        return investmentRepository.findAll(pageable);
    }

    public Investment getInvestmentById(Long id) {
        return investmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Investment not found with id: " + id
        ));
    }

    public List<Investment> getInvestmentsByInvestorId(String investorId) {
        return investmentRepository.findByInvestorId(investorId);
    }

    public Investment saveInvestment(Investment investment) {
        return investmentRepository.save(investment);
    }
}
