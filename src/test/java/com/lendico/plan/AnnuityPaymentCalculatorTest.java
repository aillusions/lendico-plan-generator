package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class AnnuityPaymentCalculatorTest {

    private AnnuityPaymentCalculator instance = new AnnuityPaymentCalculator();

    @Test
    public void shouldCalculateAnnuityPayment() {
        Assert.assertEquals(219.36, instance.calculateAnnuityPayment(5000.0, 5.0, 2), 0);
    }
}
