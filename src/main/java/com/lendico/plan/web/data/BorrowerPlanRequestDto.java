package com.lendico.plan.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerPlanRequestDto {

    @NotNull
    @Min(1)
    private Double loanAmount;

    @NotNull
    @Max(100)
    @Min(0)
    private Double nominalRate;

    @NotNull
    @Min(1)
    private Integer duration;

    @NotNull
    private LocalDate startDate;
}
