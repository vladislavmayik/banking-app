package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Currency;

import java.util.Optional;

public interface ICurrencyDAO extends IBaseDAO<Currency> {

    Optional<Currency> findByCurrencyCode(String currencyCode);

    Optional<Currency> findByName(String name);
}