package com.solvd.bankingapp.mvc;

import com.solvd.bankingapp.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountView {

    private static final Logger LOGGER = LogManager.getLogger(AccountView.class);

    public void displayAccount(Account account) {
        LOGGER.info("=== Account Details ===");
        LOGGER.info("ID: {}", account.getAccountId());
        LOGGER.info("Account Number: {}", account.getAccountNumber());
        LOGGER.info("Type: {}", account.getAccountType());
        LOGGER.info("Balance: {}", account.getBalance());
        LOGGER.info("Status: {}", account.getStatus());
    }

    public void displayMessage(String message) {
        LOGGER.info(message);
    }
}