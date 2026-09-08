package com.enviro.assessment.junior.moipone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.enviro.assessment.junior.moipone.entity.Withdrawal;
import com.enviro.assessment.junior.moipone.repository.WithdrawalRepository;

@Service
public class ReportService {

    private final WithdrawalRepository withdrawalRepository;

    public ReportService(WithdrawalRepository withdrawalRepository) {
        this.withdrawalRepository = withdrawalRepository;
    }

    public List<Withdrawal> getWithdrawalsForReport(String investorId) {

        if (investorId == null || investorId.isBlank()) {
            return withdrawalRepository.findAll();
        }

        return withdrawalRepository.findByInvestorId(investorId);
    }
}
