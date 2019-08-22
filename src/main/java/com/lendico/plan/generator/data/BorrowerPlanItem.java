package com.lendico.plan.generator.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
