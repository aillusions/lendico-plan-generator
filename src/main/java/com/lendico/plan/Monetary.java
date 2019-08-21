package com.lendico.plan;

import java.math.BigDecimal;

public final class Monetary {

    private final BigDecimal value;

    public Monetary(BigDecimal value) {
        this.value = value;
    }

    public Monetary(double value) {
        this(BigDecimal.valueOf(value));
    }

    public double toTruncated() {
        return toCentsInt() / 100.0;
    }

    public double toCentsDouble() {
        return value.multiply(BigDecimal.valueOf(100)).doubleValue();
    }

    public int toCentsInt() {
        return (int) Math.round(value.multiply(BigDecimal.valueOf(100)).doubleValue());
    }
}
