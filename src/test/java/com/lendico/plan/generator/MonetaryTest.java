package com.lendico.plan.generator;

import com.lendico.plan.generator.data.Monetary;
import org.junit.Assert;
import org.junit.Test;

public class MonetaryTest {

    @Test
    public void testMoneyZero() {
        Assert.assertEquals(0.0, new Monetary().toTruncated(), 0.0);
    }

    @Test
    public void testMoneyRoundDown() {
        Assert.assertEquals(123.45, new Monetary(123.451).toTruncated(), 0.0);
    }

    @Test
    public void testMoneyRoundUp() {
        Assert.assertEquals(123.46, new Monetary(123.457).toTruncated(), 0.0);
    }
}
