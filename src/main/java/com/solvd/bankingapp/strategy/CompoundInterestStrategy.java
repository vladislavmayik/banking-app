package com.solvd.bankingapp.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompoundInterestStrategy implements IInterestStrategy {

    @Override
    public BigDecimal calculate(BigDecimal principal, double rate, int years) {
        double result = principal.doubleValue() * Math.pow(1 + rate, years) - principal.doubleValue();
        return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP);
    }
}