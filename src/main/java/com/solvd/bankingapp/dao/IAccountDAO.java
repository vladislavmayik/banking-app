package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountDAO extends IBaseDAO<Account> {

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Long customerId);
}
