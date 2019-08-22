package com.lendico.plan.web.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lendico.plan.web.serializer.CustomDateTimeSerializer;
import com.lendico.plan.web.serializer.CustomToStringSerializer;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BorrowerPlanItemDto {

    @JsonSerialize(using = CustomToStringSerializer.class)
    private Double borrowerPaymentAmount;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    private ZonedDateTime date;

    @JsonSerialize(using = CustomToStringSerializer.class)
    private Double initialOutstandingPrincipal;

    @JsonSerialize(using = CustomToStringSerializer.class)
    private Double interest;

    @JsonSerialize(using = CustomToStringSerializer.class)
    private Double principal;

    @JsonSerialize(using = CustomToStringSerializer.class)
    private Double remainingOutstandingPrincipal;
}
