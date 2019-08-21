package com.lendico.plan;

import org.springframework.stereotype.Service;

@Service
public class AnnuityPaymentCalculator {

    /**
     * @param loanAmount    - Loan Amount
     * @param ratePerPeriod - Interest / Return Rate per calculated period
     * @param duration      - Length of Annuity - number of periods
     * @return
     */
    public double calculateAnnuityPayment(double loanAmount, double ratePerPeriod, int duration) {
        double ratePerPeriodRatio = ratePerPeriod;
        return (loanAmount * ratePerPeriodRatio) / (1 - Math.pow(1 + ratePerPeriodRatio, -duration));
    }

}
