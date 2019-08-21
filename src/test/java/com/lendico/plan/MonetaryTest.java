package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class MonetaryTest {


    @Test
    public void testMoneyRaw() {
        Assert.assertEquals(123.451, new Monetary(123.451).getRawValue(), 0.0);
    }

    @Test
    public void testMoneyRoundDown() {
        Assert.assertEquals(12345.1, new Monetary(123.451).centsDouble(), 0.0);
        Assert.assertEquals(12345, new Monetary(123.451).centsInt());
    }

    @Test
    public void testMoneyRoundUp() {
        Assert.assertEquals(12345.7, new Monetary(123.457).centsDouble(), 0.0);
        Assert.assertEquals(12346, new Monetary(123.457).centsInt());
    }
}
