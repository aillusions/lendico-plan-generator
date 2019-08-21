package com.lendico.plan.web;

import java.util.LinkedList;
import java.util.List;

public class BorrowerPlanDto {

    private List<BorrowerPlanItemDto> borrowerPlanItems = new LinkedList<>();

    public List<BorrowerPlanItemDto> getBorrowerPlanItems() {
        return borrowerPlanItems;
    }

    public void setBorrowerPlanItems(List<BorrowerPlanItemDto> borrowerPlanItems) {
        this.borrowerPlanItems = borrowerPlanItems;
    }
}
