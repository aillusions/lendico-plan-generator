package com.lendico.plan.generator;

import com.lendico.plan.generator.data.BorrowerPlanItem;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class PlanGeneratorTest {

    private static final double SOME_LOAN = 1000;
    private static final double SOME_RATE = 0.05;
    private static final int SOME_DURATION = 12;
    private static final LocalDate SOME_DATE = LocalDate.now();

    private static final int ZERO = 0;
    private static final int SOME_NEGATIVE = -2;
    private static final double SOME_POSITIVE_MORE_THAN_ONE = 1.2;

    private static final double REFERENCE_LOAN = 5000.0;
    private static final double REFERENCE_RATE = 0.05;
    private static final int REFERENCE_DURATION = 24;

    private static final double ACCEPTED_DELTA = 0.0;

    private PlanGenerator instance = new PlanGenerator();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNegativeLoan() {
        Assert.assertNotNull(instance.generatePlan(SOME_NEGATIVE, SOME_RATE, SOME_DURATION, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnZeroLoan() {
        Assert.assertNotNull(instance.generatePlan(ZERO, SOME_RATE, SOME_DURATION, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNegativeInterest() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_NEGATIVE, SOME_DURATION, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnZeroInterest() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, ZERO, SOME_DURATION, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInterest() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_POSITIVE_MORE_THAN_ONE, SOME_DURATION, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnNegativeDuration() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_RATE, SOME_NEGATIVE, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnZeroDuration() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_RATE, ZERO, SOME_DATE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionOnInvalidStartData() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_RATE, ZERO, null));
    }

    @Test
    public void shouldGeneratePlan() {
        Assert.assertNotNull(instance.generatePlan(SOME_LOAN, SOME_RATE, SOME_DURATION, SOME_DATE));
    }

    @Test
    public void numberOfItemsShouldBeCorresponding() {
        Assert.assertEquals(1, instance.generatePlan(SOME_LOAN, SOME_RATE, 1, SOME_DATE).size());
        Assert.assertEquals(12, instance.generatePlan(SOME_LOAN, SOME_RATE, 12, SOME_DATE).size());
        Assert.assertEquals(24, instance.generatePlan(SOME_LOAN, SOME_RATE, 24, SOME_DATE).size());
    }

    @Test
    public void testGeneratedPlanItems() {
        List<BorrowerPlanItem> plan = instance.generatePlan(REFERENCE_LOAN, REFERENCE_RATE, REFERENCE_DURATION, SOME_DATE);

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
        Assert.assertEquals(219.36, instance.deriveAnnuity(REFERENCE_LOAN, REFERENCE_RATE, REFERENCE_DURATION).toTruncated(), ACCEPTED_DELTA);
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
}
