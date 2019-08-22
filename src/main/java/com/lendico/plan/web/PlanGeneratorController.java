package com.lendico.plan.web;

import com.lendico.plan.generator.PlanGenerator;
import com.lendico.plan.generator.data.BorrowerPlanItem;
import com.lendico.plan.web.data.BorrowerPlanDto;
import com.lendico.plan.web.data.BorrowerPlanRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlanGeneratorController {

    private static final Logger logger = LoggerFactory.getLogger(PlanGeneratorController.class);

    @Autowired
    protected PlanGenerator generator;

    @Autowired
    protected DataMapper dataMapper;

    @PostMapping(path = "/generate-plan", headers = "Accept=application/json")
    @ResponseBody
    public BorrowerPlanDto generatePlan(@RequestBody @Valid BorrowerPlanRequestDto requestDto) {

        logger.debug("Generate plan request accepted: {}", requestDto);

        List<BorrowerPlanItem> generatedItems = generator.generatePlan(
                requestDto.getLoanAmount(),
                requestDto.getNominalRate(),
                requestDto.getDuration(),
                requestDto.getStartDate().toLocalDate()
        );

        return dataMapper.mapBorrowerPlanDto(generatedItems);
    }
}
