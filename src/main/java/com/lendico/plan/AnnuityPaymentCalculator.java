package com.lendico.plan;

import org.springframework.stereotype.Service;

@Service
public class AnnuityPaymentCalculator {

    /**
     * @param loanAmount          - Loan Amount
     * @param interestRatePerLoan - Interest / Return Rate per calculated period
     * @param numberOfRepayments  - Length of Annuity - number of periods
     * @return
     */
    public double calculateAnnuityPayment(double loanAmount, double interestRatePerLoan, int numberOfRepayments) {
        double interestRatePerPeriod = interestRatePerLoan / numberOfRepayments;
        return (loanAmount * interestRatePerPeriod) / (1 - Math.pow(1 + interestRatePerPeriod, -numberOfRepayments));
    }

}
