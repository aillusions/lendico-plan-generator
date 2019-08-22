package com.lendico.plan.web.data;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BorrowerPlanRequestDto {

    @NotNull
    @Min(1)
    private Double loanAmount;

    @NotNull
    private Double nominalRate;

    @NotNull
    @Min(1)
    private Integer duration;

    @NotNull
    private LocalDate startDate;
}
