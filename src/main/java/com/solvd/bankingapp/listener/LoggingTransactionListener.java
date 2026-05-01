package com.solvd.bankingapp.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTransactionListener implements TransactionListener {

    private static final Logger LOGGER = LogManager.getLogger(LoggingTransactionListener.class);

    @Override
    public void onTransactionCreated(TransactionEvent event) {
        LOGGER.info("Transaction created - ID: {}, Amount: {}, Type: {}, Status: {}",
                event.getTransaction().getTransactionId(),
                event.getTransaction().getAmount(),
                event.getTransaction().getType(),
                event.getTransaction().getStatus());
    }
}