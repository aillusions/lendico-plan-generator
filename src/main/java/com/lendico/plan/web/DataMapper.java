package com.lendico.plan.web;

import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.web.data.BorrowerPlanDto;
import com.lendico.plan.web.data.BorrowerPlanItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataMapper {

    public BorrowerPlanDto mapBorrowerPlanDto(List<BorrowerPlanItem> generatedItems) {
        BorrowerPlanDto rv = new BorrowerPlanDto();
        rv.setBorrowerPlanItems(generatedItems.stream().map(this::mapBorrowerPlanItemDto).collect(Collectors.toList()));
        return rv;
    }

    public BorrowerPlanItemDto mapBorrowerPlanItemDto(BorrowerPlanItem item) {
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
