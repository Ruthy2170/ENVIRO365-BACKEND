package com.enviro.assessment.junior.moipone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.junior.moipone.entity.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {

    List<Withdrawal> findByInvestorId(String investorId);
}
