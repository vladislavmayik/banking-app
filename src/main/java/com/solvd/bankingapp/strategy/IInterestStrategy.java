package com.solvd.bankingapp.strategy;

import java.math.BigDecimal;

public interface IInterestStrategy {
    BigDecimal calculate(BigDecimal principal, double rate, int years);
}