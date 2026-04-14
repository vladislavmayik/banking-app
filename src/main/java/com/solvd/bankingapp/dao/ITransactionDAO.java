package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Transaction;

import java.util.List;

public interface ITransactionDAO extends IBaseDAO<Transaction> {

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByStatus(String status);

    List<Transaction> findByType(String type);
}