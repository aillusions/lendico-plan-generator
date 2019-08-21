package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class PlanGeneratorTest {

    private PlanGenerator instance = new PlanGenerator();

    @Test
    public void shouldGeneratePlan() {
        Assert.assertNotNull(instance.generatePlan(100, 1, 12, null));
    }

    @Test
    public void numberOfItemsShouldBeCorresponding() {
        Assert.assertEquals(12, instance.generatePlan(100, 1, 12, null).size());
        Assert.assertEquals(24, instance.generatePlan(100, 1, 24, null).size());
    }
}
