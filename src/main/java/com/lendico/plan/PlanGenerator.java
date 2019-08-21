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

    /**
     * In order to inform borrowers about the final repayment schedule, we need to have pre-calculated
     * repayment plans throughout the lifetime of a loan.
     *
     * @param loanAmount  - total loan amount ("total principal amount")
     * @param nominalRate - nominal interest rate
     * @param duration    - duration (number of instalments in months)
     * @param startDate   - Date of Disbursement/Payout
     * @return
     */
    public List<BorrowerPlanItem> generatePlan(double loanAmount, double nominalRate, int duration, LocalDate startDate) {
        return new ArrayList<>();
    }

}
