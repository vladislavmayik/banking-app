package com.solvd.bankingapp.patterns.listener;

public interface TransactionListener {
    void onTransactionCreated(TransactionEvent event);
}