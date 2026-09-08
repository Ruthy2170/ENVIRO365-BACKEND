package com.enviro.assessment.junior.moipone.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.entity.Investor;
import com.enviro.assessment.junior.moipone.entity.Withdrawal;
import com.enviro.assessment.junior.moipone.exception.ResourceNotFoundException;
import com.enviro.assessment.junior.moipone.repository.InvestmentRepository;
import com.enviro.assessment.junior.moipone.repository.InvestorRepository;
import com.enviro.assessment.junior.moipone.repository.WithdrawalRepository;

@Service
public class WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;
    private final InvestorRepository investorRepository;
    private final InvestmentRepository investmentRepository;

    public WithdrawalService(
            WithdrawalRepository withdrawalRepository,
            InvestorRepository investorRepository,
            InvestmentRepository investmentRepository) {

        this.withdrawalRepository = withdrawalRepository;
        this.investorRepository = investorRepository;
        this.investmentRepository = investmentRepository;
    }

    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalRepository.findAll();
    }

    public Withdrawal getWithdrawalById(Long id) {
        return withdrawalRepository.findById(id).orElse(null);
    }

    @Transactional
    public Withdrawal saveWithdrawal(Withdrawal withdrawal) {

        Investor investor = investorRepository.findByInvestorId(
                withdrawal.getInvestorId()
        );

        if (investor == null) {
            throw new ResourceNotFoundException("Investor not found");
        }
        Investment investment = investmentRepository.findById(
                Long.valueOf(withdrawal.getInvestmentId())
        ).orElse(null);

        if (investment == null) {
            throw new ResourceNotFoundException("Investment not found");
        }

        if (withdrawal.getWithdrawalType().equalsIgnoreCase("Retirement")
                && investor.getAge() <= 65) {
            throw new IllegalArgumentException(
                    "Retirement withdrawals are only allowed for investors over 65"
            );
        }

        if (withdrawal.getAmount() > investment.getCurrentValue()) {
            throw new IllegalArgumentException(
                    "Withdrawal amount cannot exceed the available balance"
            );
        }

        if (withdrawal.getAmount() > investment.getCurrentValue() * 0.90) {
            throw new IllegalArgumentException(
                    "Withdrawal amount cannot exceed 90% of the available balance"
            );
        }
        double remainingBalance
                = investment.getCurrentValue() - withdrawal.getAmount();

        withdrawal.setRemainingBalance(remainingBalance);

        investment.setCurrentValue(remainingBalance);

        investmentRepository.save(investment);
        withdrawal.setRequestDate(LocalDateTime.now());

        return withdrawalRepository.save(withdrawal);
    }
}
