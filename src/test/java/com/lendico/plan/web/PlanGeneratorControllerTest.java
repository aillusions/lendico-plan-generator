package com.lendico.plan.web;

import com.lendico.plan.generator.PlanGenerator;
import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.web.data.BorrowerPlanRequestDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class PlanGeneratorControllerTest {

    private static final double SOME_DOUBLE = 1.12d;
    private static final int SOME_INT = 12;
    private static final LocalDate SOME_DATE = LocalDate.now();
    private static final ZonedDateTime SOME_DATE_TIME = ZonedDateTime.now();

    private final PlanGeneratorController controller = new PlanGeneratorController();

    @Mock
    private PlanGenerator planGenerator;

    @Mock
    private DataMapper dataMapper;

    @Captor
    private ArgumentCaptor<List<BorrowerPlanItem>> captor;

    private List<BorrowerPlanItem> GENERATED_ITEMS = Arrays.asList(
            new BorrowerPlanItem(SOME_DOUBLE, SOME_DATE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE, SOME_DOUBLE)
    );

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(planGenerator.generatePlan(SOME_DOUBLE, SOME_DOUBLE, SOME_INT, SOME_DATE)).thenReturn(GENERATED_ITEMS);

        controller.generator = planGenerator;
        controller.dataMapper = dataMapper;
    }

    @Test
    public void shouldCallPlanGeneratorAndDataMapper() {

        BorrowerPlanRequestDto requestDto = new BorrowerPlanRequestDto(
                SOME_DOUBLE,
                SOME_DOUBLE,
                SOME_INT,
                SOME_DATE_TIME);

        controller.generatePlan(requestDto);

        Mockito.verify(planGenerator, Mockito.times(1)).generatePlan(
                SOME_DOUBLE,
                SOME_DOUBLE,
                SOME_INT,
                SOME_DATE);

        Mockito.verify(dataMapper, Mockito.times(1)).mapBorrowerPlanDto(captor.capture());

        List<BorrowerPlanItem> capturedGeneratedList = captor.getValue();

        Assert.assertEquals(GENERATED_ITEMS, capturedGeneratedList);
    }
}
