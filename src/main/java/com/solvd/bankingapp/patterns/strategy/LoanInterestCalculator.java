package com.solvd.bankingapp.patterns.strategy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class LoanInterestCalculator {

    private static final Logger LOGGER = LogManager.getLogger(LoanInterestCalculator.class);

    private IInterestStrategy strategy;

    public LoanInterestCalculator(IInterestStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IInterestStrategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal calculate(BigDecimal principal, double rate, int years) {
        BigDecimal interest = strategy.calculate(principal, rate, years);
        LOGGER.info("Calculated interest: {} using {}", interest, strategy.getClass().getSimpleName());
        return interest;
    }
}