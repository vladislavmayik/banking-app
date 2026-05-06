package com.solvd.bankingapp.dao.impl;

import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;
import com.solvd.bankingapp.models.Transaction;
import com.solvd.bankingapp.util.ConnectionFactory;

import java.util.List;
import java.util.Optional;

public class MyBatisTransactionDAO implements ITransactionDAO {

    @Override
    public Transaction save(Transaction transaction) {
        ConnectionFactory.getTransactionMapper().save(transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(ConnectionFactory.getTransactionMapper().findById(id));
    }

    @Override
    public List<Transaction> findAll() {
        return ConnectionFactory.getTransactionMapper().findAll();
    }

    @Override
    public boolean update(Transaction transaction) {
        return ConnectionFactory.getTransactionMapper().update(transaction) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return ConnectionFactory.getTransactionMapper().deleteById(id) > 0;
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        return ConnectionFactory.getTransactionMapper().findByAccountId(accountId);
    }

    @Override
    public List<Transaction> findByStatus(String status) {
        return ConnectionFactory.getTransactionMapper().findByStatus(status);
    }

    @Override
    public List<Transaction> findByType(String type) {
        return ConnectionFactory.getTransactionMapper().findByType(type);
    }
}