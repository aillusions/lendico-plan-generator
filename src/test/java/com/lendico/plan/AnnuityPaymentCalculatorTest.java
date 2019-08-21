package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class AnnuityPaymentCalculatorTest {

    private AnnuityPaymentCalculator annuityPaymentCalculator = new AnnuityPaymentCalculator();

    @Test
    public void shouldCalculateAnnuityPayment1Year() {

        double loanAmount = 1000.0;
        double interestRateNominal = 0.02;
        int durationMonths = 12;

        Assert.assertEquals(84.24, annuityPaymentCalculator.calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths), 0.005);
    }

    @Test
    public void shouldCalculateAnnuityPayment2Years() {

        double loanAmount = 5000.0;
        double interestRateNominal = 0.05;
        int durationMonths = 24;

        Assert.assertEquals(219.36, annuityPaymentCalculator.calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths), 0.005);
    }
}
