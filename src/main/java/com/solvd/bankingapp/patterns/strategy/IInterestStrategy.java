package com.solvd.bankingapp.patterns.strategy;

import java.math.BigDecimal;

public interface IInterestStrategy {
    BigDecimal calculate(BigDecimal principal, double rate, int years);
}