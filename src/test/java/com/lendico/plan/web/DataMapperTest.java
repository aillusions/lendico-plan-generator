package com.lendico.plan.web;

import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.web.data.BorrowerPlanDto;
import com.lendico.plan.web.data.BorrowerPlanItemDto;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DataMapperTest {

    private DataMapper mapper = new DataMapper();

    private static final double SOME_DOUBLE = 1.12d;
    private static final int SOME_INT = 12;
    private static final LocalDate SOME_DATE = LocalDate.now();

    @Test
    public void shouldMapBorrowerPlanDtoEmpty() {
        List<BorrowerPlanItem> generatedItems = new LinkedList<>();

        BorrowerPlanDto dto = mapper.mapBorrowerPlanDto(generatedItems);
        Assert.assertEquals(0, dto.getBorrowerPlanItems().size());
    }

    @Test
    public void shouldMapBorrowerPlanDtoNonEmpty() {
        List<BorrowerPlanItem> generatedItems = new LinkedList<>();
        generatedItems.add(new BorrowerPlanItem(SOME_DOUBLE, SOME_DATE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE));
        generatedItems.add(new BorrowerPlanItem(SOME_DOUBLE, SOME_DATE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE));

        BorrowerPlanDto dto = mapper.mapBorrowerPlanDto(generatedItems);
        Assert.assertEquals(2, dto.getBorrowerPlanItems().size());

        BorrowerPlanItemDto dto1 = dto.getBorrowerPlanItems().get(0);
        BorrowerPlanItemDto dto2 = dto.getBorrowerPlanItems().get(1);

        // Testing some values to make sure mapBorrowerPlanItemDto was called
        Assert.assertEquals(String.valueOf(SOME_DOUBLE), dto1.getBorrowerPaymentAmount());
        Assert.assertEquals(String.valueOf(SOME_DOUBLE), dto2.getBorrowerPaymentAmount());
    }

    @Test
    public void shouldMapBorrowerPlanItemDto() {
        BorrowerPlanItem planItem = new BorrowerPlanItem();
        planItem.setRepaymentDate(LocalDate.now());
        planItem.setAnnuity(219.36);
        planItem.setPrincipal(198.53);
        planItem.setInterest(20.83);
        planItem.setInitialOutstandingPrincipal(5000);
        planItem.setRemainingOutstandingPrincipal(4801.47);

        BorrowerPlanItemDto dto = mapper.mapBorrowerPlanItemDto(planItem);

        Assert.assertEquals("219.36", dto.getBorrowerPaymentAmount());
        Assert.assertEquals("198.53", dto.getPrincipal());
        Assert.assertEquals("20.83", dto.getInterest());
        Assert.assertEquals("5000.0", dto.getInitialOutstandingPrincipal());
        Assert.assertEquals("4801.47", dto.getRemainingOutstandingPrincipal());
    }

}
