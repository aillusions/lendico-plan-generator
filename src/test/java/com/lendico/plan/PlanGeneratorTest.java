package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

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
    public void testComputations() {
        List<BorrowerPlanItem> plan = instance.generatePlan(5000, 0.05, 24, LocalDate.now());

        BorrowerPlanItem firstItem = plan.get(0);
        BorrowerPlanItem secondItem = plan.get(1);
        BorrowerPlanItem lastItem = plan.get(plan.size() - 1);

        Assert.assertEquals(219.36, firstItem.getAnnuity(), 0);
        Assert.assertEquals(198.53, firstItem.getPrincipal(), 0);
        Assert.assertEquals(20.83, firstItem.getInterest(), 0);
        Assert.assertEquals(5000, firstItem.getInitialOutstandingPrincipal(), 0);
        Assert.assertEquals(4801.47, firstItem.getRemainingOutstandingPrincipal(), 0);

        Assert.assertEquals(219.36, secondItem.getAnnuity(), 0);
        Assert.assertEquals(199.35, secondItem.getPrincipal(), 0);
        Assert.assertEquals(20.01, secondItem.getInterest(), 0);
        Assert.assertEquals(4801.47, secondItem.getInitialOutstandingPrincipal(), 0);
        Assert.assertEquals(4602.12, secondItem.getRemainingOutstandingPrincipal(), 0);

        Assert.assertEquals(219.28, lastItem.getAnnuity(), 0);
        Assert.assertEquals(218.37, lastItem.getPrincipal(), 0);
        Assert.assertEquals(0.91, lastItem.getInterest(), 0);
        Assert.assertEquals(218.37, lastItem.getInitialOutstandingPrincipal(), 0);
        Assert.assertEquals(0, lastItem.getRemainingOutstandingPrincipal(), 0);
    }

    @Test
    public void shouldCalculateAnnuityPayment1Year() {

        double loanAmount = 1000.0;
        double interestRateNominal = 0.02;
        int durationMonths = 12;

        Assert.assertEquals(84.24, instance.deriveAnnuity(loanAmount, interestRateNominal, durationMonths).toTruncated(), 0.0);
    }

    @Test
    public void shouldCalculateAnnuityPayment2Years() {

        double loanAmount = 5000.0;
        double interestRateNominal = 0.05;
        int durationMonths = 24;

        Assert.assertEquals(219.36, instance.deriveAnnuity(loanAmount, interestRateNominal, durationMonths).toTruncated(), 0.0);
    }
}
