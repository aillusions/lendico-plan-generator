package com.lendico.plan.web;

import com.lendico.plan.generator.PlanGenerator;
import com.lendico.plan.generator.data.BorrowerPlanItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlanGeneratorController {

    private PlanGenerator generator = new PlanGenerator();

    // http://localhost:8080/generate-plan
    @PostMapping(path = "/generate-plan")
    @ResponseBody
    public BorrowerPlanDto generatePlan(@RequestBody @Valid BorrowerPlanRequestDto requestDto) {

        double nominalInterestRate = requestDto.getNominalRate() / 100;

        List<BorrowerPlanItem> generatedItems = generator.generatePlan(
                requestDto.getLoanAmount(),
                nominalInterestRate,
                requestDto.getDuration(),
                requestDto.getStartDate());

        return mapBorrowerPlanDto(generatedItems);
    }

    private BorrowerPlanDto mapBorrowerPlanDto(List<BorrowerPlanItem> generatedItems) {
        BorrowerPlanDto rv = new BorrowerPlanDto();
        rv.setBorrowerPlanItems(generatedItems.stream().map(this::mapBorrowerPlanItemDto).collect(Collectors.toList()));
        return rv;
    }

    private BorrowerPlanItemDto mapBorrowerPlanItemDto(BorrowerPlanItem item) {
        BorrowerPlanItemDto rv = new BorrowerPlanItemDto();
        rv.setBorrowerPaymentAmount(String.valueOf(item.getAnnuity()));
        rv.setDate(item.getRepaymentDate().toString());
        rv.setInitialOutstandingPrincipal(String.valueOf(item.getInitialOutstandingPrincipal()));
        rv.setInterest(String.valueOf(item.getInterest()));
        rv.setPrincipal(String.valueOf(item.getPrincipal()));
        rv.setRemainingOutstandingPrincipal(String.valueOf(item.getRemainingOutstandingPrincipal()));
        return rv;
    }
}
