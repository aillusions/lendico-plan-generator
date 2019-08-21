package com.lendico.plan.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PlanGeneratorController {

    // http://localhost:8080/generate-plan
    @PostMapping(path = "/generate-plan")
    @ResponseBody
    public BorrowerPlanDto generatePlan(@RequestBody @Valid BorrowerPlanRequestDto requestDto) {
        return new BorrowerPlanDto();
    }
}
