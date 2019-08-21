package com.lendico.plan;

import org.springframework.stereotype.Service;

@Service
public class InterestRateCalculator {

    /**
     * @param interestRateNominal - nominal interest rate - per year
     * @param periodYears
     * @return
     */
    public double getInterestRatePerLoan(double interestRateNominal, int periodYears) {
        return interestRateNominal * periodYears;
    }
}
