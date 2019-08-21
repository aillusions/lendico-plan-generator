package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class AnnuityPaymentCalculatorTest {

    private static final int MONTHS_PER_YEAR = 12;

    private AnnuityPaymentCalculator instance = new AnnuityPaymentCalculator();

    @Test
    public void shouldCalculateAnnuityPayment() {

        double loanAmount = 5000.0;
        int periodYears = 2;
        int periodMonths = periodYears * MONTHS_PER_YEAR;

        double interestRateNominal = 0.05;
        double interestRatePerLoan = interestRateNominal * periodYears;
        double interestRatePerPeriod = interestRatePerLoan / periodMonths;

        Assert.assertEquals(219.36, instance.calculateAnnuityPayment(loanAmount, interestRatePerPeriod, periodMonths), 0.005);
    }
}
