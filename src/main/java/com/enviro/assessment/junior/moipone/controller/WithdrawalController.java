package com.enviro.assessment.junior.moipone.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enviro.assessment.junior.moipone.dto.WithdrawalRequest;
import com.enviro.assessment.junior.moipone.entity.Withdrawal;
import com.enviro.assessment.junior.moipone.service.WithdrawalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/withdrawals")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @GetMapping
    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    @PostMapping
    public Withdrawal createWithdrawal(
            @Valid @RequestBody WithdrawalRequest request) {

        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setInvestorId(request.getInvestorId());
        withdrawal.setInvestmentId(request.getInvestmentId());
        withdrawal.setAmount(request.getAmount());
        withdrawal.setWithdrawalType(request.getWithdrawalType());
        withdrawal.setReason(request.getReason());
        withdrawal.setStatus(request.getStatus());

        return withdrawalService.saveWithdrawal(withdrawal);
    }
}
