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

    private AnnuityPaymentCalculator annuityPaymentCalculator = new AnnuityPaymentCalculator();

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

        double initialAnnuityPayment = annuityPaymentCalculator.calculateAnnuityPayment(loanAmount, interestRateNominal, durationMonths);


        for (int i = 0; i < durationMonths; i++) {
            BorrowerPlanItem planItem = new BorrowerPlanItem();


            rv.add(planItem);
        }

        // For simplicity, we will have the following day convention: each month has 30 days, a year
        // has 360 days.
        // 2. Interest calculation; Interest = (Nominal-Rate * Days in Month * Initial Outstanding
        // Principal) / days in year
        // e.g. first installment Interest = (5.00 * 30 * 5000 / 360) = 2083.33333333 cents
        // 3. Principal = Annuity - Interest (if, calculated interest amount exceeds the initial outstanding
        // principal amount, take initial outstanding principal amount instead)
        // 4. Borrower Payment Amount (Annuity) = Principal + Interest

        return rv;
    }

}
