package com.lendico.plan.web.data;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class BorrowerPlanDto {

    private List<BorrowerPlanItemDto> borrowerPlanItems = new LinkedList<>();
}
