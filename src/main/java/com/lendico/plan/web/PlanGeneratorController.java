package com.lendico.plan.web;

import com.lendico.plan.generator.PlanGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PlanGeneratorController {

    private PlanGenerator generator = new PlanGenerator();

    // http://localhost:8080/generate-plan
    @PostMapping(path = "/generate-plan")
    @ResponseBody
    public BorrowerPlanDto generatePlan(@RequestBody @Valid BorrowerPlanRequestDto requestDto) {


        // TODO fixme
        // generator.generatePlan();

        return new BorrowerPlanDto();
    }
}
