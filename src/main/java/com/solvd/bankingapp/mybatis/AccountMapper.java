package com.solvd.bankingapp.mybatis;

import com.solvd.bankingapp.models.Account;
import java.util.List;

public interface AccountMapper {

    Account findById(Long id);

    List<Account> findAll();

    void save(Account account);

    int update(Account account);

    int deleteById(Long id);

    Account findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(Long customerId);
}