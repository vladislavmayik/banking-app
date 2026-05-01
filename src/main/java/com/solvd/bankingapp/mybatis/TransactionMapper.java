package com.solvd.bankingapp.mybatis;

import com.solvd.bankingapp.models.Transaction;
import java.util.List;

public interface TransactionMapper {

    Transaction findById(Long id);

    List<Transaction> findAll();

    void save(Transaction transaction);

    int update(Transaction transaction);

    int deleteById(Long id);

    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByStatus(String status);

    List<Transaction> findByType(String type);
}