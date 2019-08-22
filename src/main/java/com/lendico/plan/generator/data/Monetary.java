package com.lendico.plan.generator.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class Monetary {

    private static final double CENTS_PER_COIN = 100.0;

    private final BigDecimal value;

    public Monetary(BigDecimal value) {
        this.value = value;
    }

    public Monetary(double value) {
        this(BigDecimal.valueOf(value));
    }

    public Monetary() {
        this(BigDecimal.ZERO);
    }

    public double toTruncated() {
        return (int) Math.round(value.multiply(BigDecimal.valueOf(CENTS_PER_COIN)).doubleValue()) / CENTS_PER_COIN;
    }
}
