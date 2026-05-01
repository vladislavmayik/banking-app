package com.solvd.bankingapp.mybatis;

import com.solvd.bankingapp.dao.factory.ConnectionFactory;
import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.models.Account;

import java.util.List;
import java.util.Optional;

public class MyBatisAccountDAO implements IAccountDAO {

    @Override
    public Account save(Account account) {
        ConnectionFactory.getAccountMapper().save(account);
        return account;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(ConnectionFactory.getAccountMapper().findById(id));
    }

    @Override
    public List<Account> findAll() {
        return ConnectionFactory.getAccountMapper().findAll();
    }

    @Override
    public boolean update(Account account) {
        return ConnectionFactory.getAccountMapper().update(account) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return ConnectionFactory.getAccountMapper().deleteById(id) > 0;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(ConnectionFactory.getAccountMapper().findByAccountNumber(accountNumber));
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        return ConnectionFactory.getAccountMapper().findByCustomerId(customerId);
    }
}