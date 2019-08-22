package com.lendico.plan.generator;

import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.generator.data.PlanGeneratorException;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.List;

public class PlanGeneratorTest {

    private static final double SOME_LOAN = 1000;
    private static final double SOME_INTEREST_PERCENT = 5.00;
    private static final int SOME_DURATION = 12;
    private static final ZonedDateTime SOME_DATE = ZonedDateTime.now();

    private static final double REFERENCE_LOAN = 5000.0;
    private static final double REFERENCE_INTEREST_PERCENT = 5.00;
    private static final double REFERENCE_INTEREST_RATE = 0.05;
    private static final int REFERENCE_DURATION = 24;

    private static final double ACCEPTED_DELTA = 0.0;

    private PlanGenerator instance = new PlanGenerator();

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnNegativeLoan() {
        instance.generatePlan(-1, SOME_INTEREST_PERCENT, SOME_DURATION, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnZeroLoan() {
        instance.generatePlan(0, SOME_INTEREST_PERCENT, SOME_DURATION, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnNegativeInterest() {
        instance.generatePlan(SOME_LOAN, -1, SOME_DURATION, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnZeroInterest() {
        instance.generatePlan(SOME_LOAN, 0, SOME_DURATION, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnNegativeDuration() {
        instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, -1, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnZeroDuration() {
        instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, 0, SOME_DATE);
    }

    @Test(expected = PlanGeneratorException.class)
    public void shouldThrowExceptionOnInvalidStartData() {
        instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, SOME_DURATION, null);
    }

    @Test
    public void numberOfItemsShouldBeCorresponding() {
        Assert.assertEquals(1, instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, 1, SOME_DATE).size());
        Assert.assertEquals(12, instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, 12, SOME_DATE).size());
        Assert.assertEquals(24, instance.generatePlan(SOME_LOAN, SOME_INTEREST_PERCENT, 24, SOME_DATE).size());
    }

    @Test
    public void testGeneratedPlanItems() {
        List<BorrowerPlanItem> plan = instance.generatePlan(REFERENCE_LOAN, REFERENCE_INTEREST_PERCENT, REFERENCE_DURATION, SOME_DATE);

        Assert.assertEquals(REFERENCE_DURATION, plan.size());

        BorrowerPlanItem firstItem = plan.get(0);
        BorrowerPlanItem secondItem = plan.get(1);
        BorrowerPlanItem lastItem = plan.get(plan.size() - 1);

        Assert.assertEquals(219.36, firstItem.getAnnuity(), ACCEPTED_DELTA);
        Assert.assertEquals(198.53, firstItem.getPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(20.83, firstItem.getInterest(), ACCEPTED_DELTA);
        Assert.assertEquals(5000, firstItem.getInitialOutstandingPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(4801.47, firstItem.getRemainingOutstandingPrincipal(), ACCEPTED_DELTA);

        Assert.assertEquals(219.36, secondItem.getAnnuity(), ACCEPTED_DELTA);
        Assert.assertEquals(199.35, secondItem.getPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(20.01, secondItem.getInterest(), ACCEPTED_DELTA);
        Assert.assertEquals(4801.47, secondItem.getInitialOutstandingPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(4602.12, secondItem.getRemainingOutstandingPrincipal(), ACCEPTED_DELTA);

        Assert.assertEquals(219.28, lastItem.getAnnuity(), ACCEPTED_DELTA);
        Assert.assertEquals(218.37, lastItem.getPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(0.91, lastItem.getInterest(), ACCEPTED_DELTA);
        Assert.assertEquals(218.37, lastItem.getInitialOutstandingPrincipal(), ACCEPTED_DELTA);
        Assert.assertEquals(0, lastItem.getRemainingOutstandingPrincipal(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldCalculateDerivedAnnuity() {
        Assert.assertEquals(219.36, instance.deriveAnnuity(REFERENCE_LOAN, REFERENCE_INTEREST_RATE, REFERENCE_DURATION).toTruncated(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldCalculateRemainingOutstandingPrincipal() {
        Assert.assertEquals(900, instance.calculateRemainingOutstandingPrincipal(1000, 100).toTruncated(), ACCEPTED_DELTA);
        Assert.assertEquals(0, instance.calculateRemainingOutstandingPrincipal(0, 100).toTruncated(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldCalculateAnnuity() {
        Assert.assertEquals(300, instance.calculateAnnuity(200, 100).toTruncated(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldCalculatePrincipal() {
        Assert.assertEquals(100, instance.calculatePrincipal(200, 100, 1000).toTruncated(), ACCEPTED_DELTA);
        Assert.assertEquals(10, instance.calculatePrincipal(200, 100, 10).toTruncated(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldCalculateInterest() {
        Assert.assertEquals(0.83, instance.calculateInterest(0.01, 1000).toTruncated(), ACCEPTED_DELTA);
    }

    @Test
    public void shouldConvertPercentToRate() {
        Assert.assertEquals(0.9, instance.convertPercentToRate(90), ACCEPTED_DELTA);
        Assert.assertEquals(0.05, instance.convertPercentToRate(5), ACCEPTED_DELTA);
        Assert.assertEquals(0.01123, instance.convertPercentToRate(1.123), ACCEPTED_DELTA);
        Assert.assertEquals(0.006666666666, instance.convertPercentToRate(0.6666666666), ACCEPTED_DELTA);
    }
}
