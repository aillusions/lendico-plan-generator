package com.lendico.plan;

import org.junit.Assert;
import org.junit.Test;

public class MonetaryTest {


    @Test
    public void testMoneyZero() {
        Assert.assertEquals(0.0, new Monetary().toTruncated(), 0.0);
        Assert.assertEquals(0.0, new Monetary().toCentsDouble(), 0.0);
        Assert.assertEquals(0, new Monetary().toCentsInt());
    }

    @Test
    public void testMoneyRoundDown() {
        Assert.assertEquals(123.45, new Monetary(123.451).toTruncated(), 0.0);
        Assert.assertEquals(12345.1, new Monetary(123.451).toCentsDouble(), 0.0);
        Assert.assertEquals(12345, new Monetary(123.451).toCentsInt());
    }

    @Test
    public void testMoneyRoundUp() {
        Assert.assertEquals(123.46, new Monetary(123.457).toTruncated(), 0.0);
        Assert.assertEquals(12345.7, new Monetary(123.457).toCentsDouble(), 0.0);
        Assert.assertEquals(12346, new Monetary(123.457).toCentsInt());
    }
}
