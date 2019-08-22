package com.lendico.plan.web.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lendico.plan.web.serializer.CustomDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerPlanRequestDto {

    @NotNull
    @Positive
    private Double loanAmount;

    @NotNull
    @Positive
    private Double nominalRate;

    @NotNull
    @Min(1)
    private Integer duration;

    @NotNull
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private ZonedDateTime startDate;
}
