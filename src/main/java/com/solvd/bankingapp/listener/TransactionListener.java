package com.solvd.bankingapp.listener;

public interface TransactionListener {
    void onTransactionCreated(TransactionEvent event);
}