package com.lendico.plan.generator.data;

import java.math.BigDecimal;

public final class Monetary {

    private static final double CENTS_PER_COIN = 100.0;
    private static final BigDecimal CENTS_PER_COIN_BD = BigDecimal.valueOf(CENTS_PER_COIN);

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
        return toCentsInt() / CENTS_PER_COIN;
    }

    public double toCentsDouble() {
        return value.multiply(CENTS_PER_COIN_BD).doubleValue();
    }

    public int toCentsInt() {
        return (int) Math.round(value.multiply(CENTS_PER_COIN_BD).doubleValue());
    }
}
