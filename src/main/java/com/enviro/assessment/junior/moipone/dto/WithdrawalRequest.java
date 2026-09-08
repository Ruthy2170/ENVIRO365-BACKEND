package com.enviro.assessment.junior.moipone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class WithdrawalRequest {

    @NotBlank(message = "Investor ID is required")
    private String investorId;

    @NotBlank(message = "Investment ID is required")
    private String investmentId;

    @Positive(message = "Withdrawal amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Withdrawal type is required")
    private String withdrawalType;

    private String reason;

    private String status;

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWithdrawalType() {
        return withdrawalType;
    }

    public void setWithdrawalType(String withdrawalType) {
        this.withdrawalType = withdrawalType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
