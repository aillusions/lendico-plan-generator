package com.lendico.plan.web.data;

import lombok.Data;

@Data
public class BorrowerPlanItemDto {

    private String borrowerPaymentAmount;
    private String date;
    private String initialOutstandingPrincipal;
    private String interest;
    private String principal;
    private String remainingOutstandingPrincipal;
}
