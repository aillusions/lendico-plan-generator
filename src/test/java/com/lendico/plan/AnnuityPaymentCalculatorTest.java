package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnuityPaymentCalculatorTest {

    private static final int MONTHS_PER_YEAR = 12;

    @Autowired
    private AnnuityPaymentCalculator annuityPaymentCalculator;

    @Autowired
    private InterestRateCalculator interestRateCalculator;

    @Test
    public void shouldCalculateAnnuityPayment() {

        double loanAmount = 5000.0;
        int periodYears = 2;
        int periodMonths = periodYears * MONTHS_PER_YEAR;
        double interestRateNominal = 0.05;

        double interestRatePerLoan = interestRateCalculator.getInterestRatePerLoan(interestRateNominal, periodYears);
        Assert.assertEquals(219.36, annuityPaymentCalculator.calculateAnnuityPayment(loanAmount, interestRatePerLoan, periodMonths), 0.005);
    }
}
