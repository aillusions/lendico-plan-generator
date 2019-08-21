package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PlanGeneratorTest {

    private PlanGenerator instance = new PlanGenerator();

    @Test
    public void shouldGeneratePlan() {
        Assert.assertNotNull(instance.generatePlan(100, 1, 12, LocalDate.now()));
    }

    @Test
    public void numberOfItemsShouldBeCorresponding() {
        Assert.assertEquals(12, instance.generatePlan(100, 1, 12, LocalDate.now()).size());
        Assert.assertEquals(24, instance.generatePlan(100, 1, 24, LocalDate.now()).size());
    }

    @Test
    public void test() {
        instance.generatePlan(5000, 0.05, 24, LocalDate.now());
    }

    @Test
    public void shouldCalculateAnnuityPayment1Year() {

        double loanAmount = 1000.0;
        double interestRateNominal = 0.02;
        int durationMonths = 12;

        Assert.assertEquals(84.24, instance.calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths).toTruncated(), 0.0);
    }

    @Test
    public void shouldCalculateAnnuityPayment2Years() {

        double loanAmount = 5000.0;
        double interestRateNominal = 0.05;
        int durationMonths = 24;

        Assert.assertEquals(219.36, instance.calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths).toTruncated(), 0.0);
    }
}
