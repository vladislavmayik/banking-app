package com.solvd.bankingapp.patterns.mvc;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    private final IAccountDAO accountDAO;
    private final AccountView view;

    public AccountController(IAccountDAO accountDAO, AccountView view) {
        this.accountDAO = accountDAO;
        this.view = view;
    }

    public void getAccount(Long id) {
        Optional<Account> account = accountDAO.findById(id);
        if (account.isPresent()) {
            view.displayAccount(account.get());
        } else {
            view.displayMessage("Account with id " + id + " not found.");
        }
    }

    public void createAccount(Account account) {
        accountDAO.save(account);
        view.displayMessage("Account created: " + account.getAccountNumber());
    }

    public void updateAccount(Account account) {
        boolean updated = accountDAO.update(account);
        if (updated) {
            view.displayMessage("Account updated successfully.");
        } else {
            view.displayMessage("Failed to update account.");
        }
    }

    public void deleteAccount(Long id) {
        boolean deleted = accountDAO.deleteById(id);
        if (deleted) {
            view.displayMessage("Account deleted successfully.");
        } else {
            view.displayMessage("Failed to delete account.");
        }
    }
}