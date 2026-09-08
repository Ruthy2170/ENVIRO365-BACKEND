package com.enviro.assessment.junior.moipone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.junior.moipone.entity.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    List<Investment> findByInvestorId(String investorId);

}
