package com.lendico.plan.web.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lendico.plan.web.serializer.CustomDateTimeSerializer;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BorrowerPlanItemDto {

    private Double borrowerPaymentAmount;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private ZonedDateTime date;

    private Double initialOutstandingPrincipal;
    private Double interest;
    private Double principal;
    private Double remainingOutstandingPrincipal;
}
