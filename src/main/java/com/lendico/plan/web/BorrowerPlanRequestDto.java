package com.lendico.plan.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BorrowerPlanRequestDto {

    @NotEmpty
    private String loanAmount;

    @NotEmpty
    private String nominalRate;

    @NotNull
    private Integer duration;

    @NotNull
    private LocalDate startDate;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(String nominalRate) {
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
