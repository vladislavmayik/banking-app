package com.solvd.bankingapp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleInterestStrategy implements IInterestStrategy {

    @Override
    public BigDecimal calculate(BigDecimal principal, double rate, int years) {
        return principal.multiply(BigDecimal.valueOf(rate))
                .multiply(BigDecimal.valueOf(years))
                .setScale(2, RoundingMode.HALF_UP);
    }
}