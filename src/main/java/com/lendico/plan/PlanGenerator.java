package com.lendico.plan;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanGenerator {

    public List<BorrowerPlanItem> generatePlan(float loanAmount, float nominalRate, int duration, LocalDate startDate) {
        return new ArrayList<>();
    }

}
