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

    public double getRawValue() {
        return value.doubleValue();
    }

    public double centsDouble() {
        return value.multiply(BigDecimal.valueOf(100)).doubleValue();
    }

    public int centsInt() {
        return (int) Math.round(value.multiply(BigDecimal.valueOf(100)).doubleValue());
    }
}
