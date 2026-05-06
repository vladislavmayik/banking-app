package com.solvd.bankingapp.patterns.listener;

import com.solvd.bankingapp.models.Transaction;

public class TransactionEvent {

    private final Transaction transaction;

    public TransactionEvent(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}