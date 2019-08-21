package com.lendico.plan.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(Double nominalRate) {
        this.nominalRate = nominalRate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
