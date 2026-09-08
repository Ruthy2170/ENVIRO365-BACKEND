package com.enviro.assessment.junior.moipone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.enviro.assessment.junior.moipone.entity.Investment;
import com.enviro.assessment.junior.moipone.entity.Investor;
import com.enviro.assessment.junior.moipone.repository.InvestmentRepository;
import com.enviro.assessment.junior.moipone.repository.InvestorRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final InvestorRepository investorRepository;
    private final InvestmentRepository investmentRepository;

    public DataInitializer(
            InvestorRepository investorRepository,
            InvestmentRepository investmentRepository) {

        this.investorRepository = investorRepository;
        this.investmentRepository = investmentRepository;
    }

    @Override
    public void run(String... args) {

        if (investorRepository.count() == 0) {

            Investor investor1 = new Investor();
            investor1.setInvestorId("INV001");
            investor1.setName("John Smith");
            investor1.setAge(70);
            investor1.setEmail("john@example.com");

            investorRepository.save(investor1);

            Investor investor2 = new Investor();
            investor2.setInvestorId("INV002");
            investor2.setName("Sarah Jones");
            investor2.setAge(60);
            investor2.setEmail("sarah@example.com");

            investorRepository.save(investor2);

            if (investmentRepository.count() == 0) {
                Investment investment1 = new Investment();

                investment1.setInvestorId("INV001");
                investment1.setInvestmentName("Retirement Fund");
                investment1.setInvestmentType("Retirement");
                investment1.setAmountInvested(100000);
                investment1.setCurrentValue(100000);
                investment1.setStatus("Active");

                investmentRepository.save(investment1);

                Investment investment2 = new Investment();

                investment2.setInvestorId("INV002");
                investment2.setInvestmentName("Growth Fund");
                investment2.setInvestmentType("General");
                investment2.setAmountInvested(50000);
                investment2.setCurrentValue(50000);
                investment2.setStatus("Active");

                investmentRepository.save(investment2);
            }
        }
    }
}
