package com.solvd.bankingapp.patterns.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotificationTransactionListener implements TransactionListener {

    private static final Logger LOGGER = LogManager.getLogger(NotificationTransactionListener.class);

    @Override
    public void onTransactionCreated(TransactionEvent event) {
        LOGGER.info("Sending notification for transaction - Amount: {}, Type: {}",
                event.getTransaction().getAmount(),
                event.getTransaction().getType());
    }
}