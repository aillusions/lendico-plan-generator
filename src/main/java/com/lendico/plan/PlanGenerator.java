package com.lendico.plan;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates a repayment plan for an annuity loan.
 */
@Service
public class PlanGenerator {

    // For simplicity, we will have the following day convention: each month has 30 days, a year has 360 days.
    private static final int DAYS_PER_MONTH = 30;
    private static final int DAYS_PER_YEAR = 360;
    private static final int MONTHS_PER_YEAR = 12;

    /**
     * In order to inform borrowers about the final repayment schedule, we need to have pre-calculated
     * repayment plans throughout the lifetime of a loan.
     *
     * @param loanAmount          - total loan amount ("total principal amount")
     * @param nominalInterestRate - nominal interest rate (a yearly basis number)
     * @param durationMonths      - duration (number of instalments in months)
     * @param startDate           - Date of Disbursement/Payout
     * @return
     */
    public List<BorrowerPlanItem> generatePlan(double loanAmount, double nominalInterestRate, int durationMonths, LocalDate startDate) {
        List<BorrowerPlanItem> rv = new ArrayList<>();

        double initialAnnuity = calculateAnnuityPayment(loanAmount, nominalInterestRate, durationMonths).toTruncated();
        double initialOutstandingPrincipal = loanAmount;
        LocalDate payoutDate = startDate;

        for (int i = 0; i < durationMonths; i++) {
            BorrowerPlanItem planItem = new BorrowerPlanItem();
            planItem.setRepaymentDate(payoutDate);

            double interest = calculateInterest(nominalInterestRate, initialOutstandingPrincipal).toTruncated();
            planItem.setInterest(interest);

            double principal = calculatePrincipal(initialAnnuity, interest, initialOutstandingPrincipal).toTruncated();
            planItem.setPrincipal(principal);

            double annuity = calculateAnnuity(principal, interest).toTruncated();
            planItem.setAnnuity(annuity);

            planItem.setInitialOutstandingPrincipal(initialOutstandingPrincipal);

            double remainingOutstandingPrincipal = calculateRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal).toTruncated();
            planItem.setRemainingOutstandingPrincipal(remainingOutstandingPrincipal);

            System.out.println(planItem);

            rv.add(planItem);

            payoutDate = payoutDate.plusMonths(1);
            initialOutstandingPrincipal = calculateInitialOutstandingPrincipal(initialOutstandingPrincipal, principal).toTruncated();
        }

        return rv;
    }

    /**
     * Interest = (Nominal-Rate * Days in Month * Initial Outstanding Principal) / days in year
     */
    protected Monetary calculateInterest(double nominalInterestRate, double initialOutstandingPrincipal) {
        return new Monetary((nominalInterestRate * DAYS_PER_MONTH * initialOutstandingPrincipal) / DAYS_PER_YEAR);
    }

    /**
     * Principal = Annuity - Interest (if, calculated interest amount exceeds the initial outstanding principal amount, take initial outstanding principal amount instead)
     */
    protected Monetary calculatePrincipal(double annuity, double interest, double initialOutstandingPrincipal) {
        if (interest > initialOutstandingPrincipal) {
            return new Monetary(initialOutstandingPrincipal);
        } else {
            return new Monetary(annuity - interest);
        }
    }

    /**
     * Annuity = Principal + Interest
     */
    protected Monetary calculateAnnuity(double principal, double interest) {
        return new Monetary(principal + interest);
    }

    /**
     *
     */
    protected Monetary calculateAnnuityPayment(double loanAmount, double interestRateNominal, int numberOfRepayments) {
        double interestRatePerPeriod = interestRateNominal / MONTHS_PER_YEAR;
        return new Monetary((loanAmount * interestRatePerPeriod) / (1 - Math.pow(1 + interestRatePerPeriod, -numberOfRepayments)));
    }

    protected Monetary calculateInitialOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        return new Monetary(initialOutstandingPrincipal - principal);
    }

    protected Monetary calculateRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        if (principal > initialOutstandingPrincipal) {
            return new Monetary();
        } else {
            return new Monetary(initialOutstandingPrincipal - principal);
        }
    }

}
