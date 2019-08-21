package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class PlanGeneratorTest {

    private PlanGenerator instance = new PlanGenerator();

    @Test
    public void shouldGeneratePlan() {
        Assert.assertNotNull(instance.generatePlan(0, 0, 0, null));
    }
}
