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
     * @param interestRateNominal - nominal interest rate (a yearly basis number)
     * @param durationMonths      - duration (number of instalments in months)
     * @param startDate           - Date of Disbursement/Payout
     * @return
     */
    public List<BorrowerPlanItem> generatePlan(double loanAmount, double interestRateNominal, int durationMonths, LocalDate startDate) {
        List<BorrowerPlanItem> rv = new ArrayList<>();

        double initialAnnuity =calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths);

        double initialOutstandingPrincipal = loanAmount;
        LocalDate payoutDate = startDate;

        for (int i = 0; i < durationMonths; i++) {
            BorrowerPlanItem planItem = new BorrowerPlanItem();
            planItem.setRepaymentDate(payoutDate);

            // Interest calculation; Interest = (Nominal-Rate * Days in Month * Initial Outstanding Principal) / days in year
            double interest = interestRateNominal * DAYS_PER_MONTH * initialOutstandingPrincipal / DAYS_PER_YEAR;
            planItem.setInterest(interest);

            double annuity = initialAnnuity <= initialOutstandingPrincipal ? initialAnnuity : initialOutstandingPrincipal;
            planItem.setAnnuity(annuity);

            // Principal = Annuity - Interest (if, calculated interest amount exceeds the initial outstanding principal amount, take initial outstanding principal amount instead)
            double principal = annuity - interest;
            planItem.setPrincipal(principal);

            System.out.println(planItem);

            rv.add(planItem);

            payoutDate = payoutDate.plusMonths(1);
            initialOutstandingPrincipal = loanAmount - principal;
        }


        return rv;
    }

    /**
     * @param loanAmount          - Loan Amount
     * @param interestRateNominal - Interest Rate (per year)
     * @param numberOfRepayments  - Length of Annuity (number of periods in months)
     * @return
     */
    public double calculateAnnuityPayment(double loanAmount, double interestRateNominal, int numberOfRepayments) {
        double interestRatePerPeriod = interestRateNominal / MONTHS_PER_YEAR;
        return (loanAmount * interestRatePerPeriod) / (1 - Math.pow(1 + interestRatePerPeriod, -numberOfRepayments));
    }

}
