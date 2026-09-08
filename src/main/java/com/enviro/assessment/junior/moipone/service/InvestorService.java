package com.enviro.assessment.junior.moipone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enviro.assessment.junior.moipone.entity.Investor;
import com.enviro.assessment.junior.moipone.repository.InvestorRepository;

@Service
public class InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public Investor getInvestorById(Long id) {
        return investorRepository.findById(id).orElse(null);
    }

    public Investor saveInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    public Investor getInvestorByInvestorId(String investorId) {
        return investorRepository.findByInvestorId(investorId);
    }
}
