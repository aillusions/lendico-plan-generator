package com.lendico.plan.generator;

import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.generator.data.Monetary;
import com.lendico.plan.generator.data.PlanGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Calculates a repayment plan for an annuity loan.
 */
@Service
public class PlanGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PlanGenerator.class);

    // For simplicity, we will have the following day convention: each month has 30 days, a year has 360 days.
    private static final int DAYS_PER_MONTH = 30;
    private static final int DAYS_PER_YEAR = 360;
    private static final int MONTHS_PER_YEAR = 12;

    /**
     * In order to inform borrowers about the final repayment schedule, we need to have pre-calculated
     * repayment plans throughout the lifetime of a loan.
     *
     * @param loanAmount             - total loan amount ("total principal amount")
     * @param nominalInterestPercent - nominal interest percent (a yearly basis number)
     * @param durationMonths         - duration (number of instalments in months)
     * @param startDate              - Date of Disbursement/Payout
     */
    public List<BorrowerPlanItem> generatePlan(double loanAmount,
                                               double nominalInterestPercent,
                                               int durationMonths,
                                               ZonedDateTime startDate) {
        logger.debug(
                "generatePlan called with arguments: loanAmount: {}, nominalInterestRate: {}, durationMonths: {}, startDate: {}",
                loanAmount,
                nominalInterestPercent,
                durationMonths,
                startDate);

        double nominalInterestRate = convertPercentToRate(nominalInterestPercent);

        assertValidArguments(loanAmount, nominalInterestRate, durationMonths, startDate);

        final List<BorrowerPlanItem> rv = new LinkedList<>();

        final double derivedAnnuity = deriveAnnuity(loanAmount, nominalInterestRate, durationMonths).toTruncated();

        double outstandingPrincipal = loanAmount;

        for (int i = 0; i < durationMonths; i++) {

            final ZonedDateTime payoutDate = startDate.plusMonths(i);

            final BorrowerPlanItem planItem = createBorrowerPlanItem(
                    nominalInterestRate,
                    payoutDate,
                    derivedAnnuity,
                    outstandingPrincipal);

            outstandingPrincipal = new Monetary(outstandingPrincipal - planItem.getPrincipal()).toTruncated();

            rv.add(planItem);
        }

        return rv;
    }

    protected double convertPercentToRate(double percent) {
        return BigDecimal.valueOf(percent).divide(BigDecimal.valueOf(100)).doubleValue();
    }

    protected void assertValidArguments(double loanAmount,
                                        double nominalInterestRate,
                                        int durationMonths,
                                        ZonedDateTime startDate) {

        if (loanAmount <= 0) {
            throw new PlanGeneratorException(
                    String.format("Unable to generate plan: too small loanAmount: {}", loanAmount)
            );
        }

        if (nominalInterestRate <= 0) {
            throw new PlanGeneratorException(
                    String.format("Unable to generate plan: nominalInterestRate expected to be in range [0..1]. Actual: {}", nominalInterestRate)
            );
        }

        if (durationMonths <= 0) {
            throw new PlanGeneratorException(
                    String.format("Unable to generate plan: too small durationMonths: {}", durationMonths)
            );
        }

        if (startDate == null) {
            throw new PlanGeneratorException("Unable to generate plan: startDate is not provided.");
        }
    }

    protected BorrowerPlanItem createBorrowerPlanItem(double nominalInterestRate,
                                                      ZonedDateTime payoutDate,
                                                      double derivedAnnuity,
                                                      double outstandingPrincipal) {

        BorrowerPlanItem planItem = new BorrowerPlanItem();
        planItem.setRepaymentDate(payoutDate);

        planItem.setInitialOutstandingPrincipal(outstandingPrincipal);

        double interest = calculateInterest(nominalInterestRate, outstandingPrincipal).toTruncated();
        planItem.setInterest(interest);

        double principal = calculatePrincipal(derivedAnnuity, interest, outstandingPrincipal).toTruncated();
        planItem.setPrincipal(principal);

        double annuity = calculateAnnuity(principal, interest).toTruncated();
        planItem.setAnnuity(annuity);

        double remainingOutstandingPrincipal = calculateRemainingOutstandingPrincipal(outstandingPrincipal, principal).toTruncated();
        planItem.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);

        return planItem;
    }

    /**
     * Interest = (Nominal-Rate * Days in Month * Initial Outstanding Principal) / days in year
     */
    protected Monetary calculateInterest(double nominalInterestRate, double initialOutstandingPrincipal) {
        return new Monetary((nominalInterestRate * DAYS_PER_MONTH * initialOutstandingPrincipal) / DAYS_PER_YEAR);
    }

    /**
     * Principal = Annuity - Interest (if, calculated interest amount exceeds the initial outstanding principal amount,
     * take initial outstanding principal amount instead)
     */
    protected Monetary calculatePrincipal(double derivedAnnuity, double interest, double initialOutstandingPrincipal) {
        double rv = derivedAnnuity - interest;
        if (rv > initialOutstandingPrincipal) {
            return new Monetary(initialOutstandingPrincipal);
        } else {
            return new Monetary(rv);
        }
    }

    /**
     * Annuity = Principal + Interest
     */
    protected Monetary calculateAnnuity(double principal, double interest) {
        return new Monetary(principal + interest);
    }

    /**
     * see http://financeformulas.net/Annuity_Payment_Formula.html as reference
     */
    protected Monetary deriveAnnuity(double loanAmount, double interestRateNominal, int numberOfRepayments) {
        double interestRatePerPeriod = interestRateNominal / MONTHS_PER_YEAR;
        double divident = loanAmount * interestRatePerPeriod;
        double divisor = 1 - Math.pow(1 + interestRatePerPeriod, -numberOfRepayments);
        return new Monetary(divident / divisor);
    }

    protected Monetary calculateRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        if (principal > initialOutstandingPrincipal) {
            return new Monetary();
        } else {
            return new Monetary(initialOutstandingPrincipal - principal);
        }
    }

}
