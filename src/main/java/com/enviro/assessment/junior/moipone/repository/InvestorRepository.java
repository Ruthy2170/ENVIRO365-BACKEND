package com.enviro.assessment.junior.moipone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.junior.moipone.entity.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {

    Investor findByInvestorId(String investorId);
}
