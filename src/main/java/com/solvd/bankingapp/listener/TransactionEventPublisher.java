package com.solvd.bankingapp.listener;

import com.solvd.bankingapp.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionEventPublisher {

    private final List<TransactionListener> listeners = new ArrayList<>();

    public void addListener(TransactionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(TransactionListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(Transaction transaction) {
        TransactionEvent event = new TransactionEvent(transaction);
        listeners.forEach(listener -> listener.onTransactionCreated(event));
    }
}