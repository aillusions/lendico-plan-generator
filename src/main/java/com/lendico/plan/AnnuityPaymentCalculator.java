package com.lendico.plan;

import org.springframework.stereotype.Service;

@Service
public class AnnuityPaymentCalculator {

    private static final int MONTHS_PER_YEAR = 12;

    /**
     * @param loanAmount          - Loan Amount
     * @param interestRateNominal - Interest Rate (per year)
     * @param numberOfRepayments  - Length of Annuity (number of periods in months)
     * @return
     */
    public double calculateAnnuityPayment(double loanAmount, double interestRateNominal, int numberOfRepayments) {
        double interestRatePerPeriod = interestRateNominal / MONTHS_PER_YEAR;
        return (loanAmount * interestRatePerPeriod) / (1 - Math.pow(1 + interestRatePerPeriod, -numberOfRepayments));
    }

}
