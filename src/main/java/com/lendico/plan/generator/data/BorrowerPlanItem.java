package com.lendico.plan.generator.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowerPlanItem {

    /**
     * Borrower payment amount
     */
    private double annuity;

    private LocalDate repaymentDate;
    private double initialOutstandingPrincipal;
    private double interest;
    private double principal;
    private double remainingOutstandingPrincipal;
}
