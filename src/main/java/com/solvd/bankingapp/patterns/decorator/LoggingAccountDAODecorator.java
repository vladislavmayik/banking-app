package com.solvd.bankingapp.patterns.decorator;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class LoggingAccountDAODecorator extends AccountDAODecorator {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAccountDAODecorator.class);

    public LoggingAccountDAODecorator(IAccountDAO accountDAO) {
        super(accountDAO);
    }

    @Override
    public Account save(Account account) {
        LOGGER.info("Saving account: {}", account.getAccountNumber());
        Account saved = accountDAO.save(account);
        LOGGER.info("Account saved with id: {}", saved.getAccountId());
        return saved;
    }

    @Override
    public Optional<Account> findById(Long id) {
        LOGGER.info("Finding account by id: {}", id);
        Optional<Account> account = accountDAO.findById(id);
        account.ifPresentOrElse(
                a -> LOGGER.info("Account found: {}", a.getAccountNumber()),
                () -> LOGGER.info("Account not found for id: {}", id)
        );
        return account;
    }

    @Override
    public List<Account> findAll() {
        LOGGER.info("Finding all accounts");
        List<Account> accounts = accountDAO.findAll();
        LOGGER.info("Found {} accounts", accounts.size());
        return accounts;
    }

    @Override
    public boolean update(Account account) {
        LOGGER.info("Updating account: {}", account.getAccountNumber());
        boolean result = accountDAO.update(account);
        LOGGER.info("Account update result: {}", result);
        return result;
    }

    @Override
    public boolean deleteById(Long id) {
        LOGGER.info("Deleting account by id: {}", id);
        boolean result = accountDAO.deleteById(id);
        LOGGER.info("Account delete result: {}", result);
        return result;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        LOGGER.info("Finding account by number: {}", accountNumber);
        Optional<Account> account = accountDAO.findByAccountNumber(accountNumber);
        account.ifPresentOrElse(
                a -> LOGGER.info("Account found: {}", a.getAccountNumber()),
                () -> LOGGER.info("Account not found for number: {}", accountNumber)
        );
        return account;
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        LOGGER.info("Finding accounts for customer id: {}", customerId);
        List<Account> accounts = accountDAO.findByCustomerId(customerId);
        LOGGER.info("Found {} accounts for customer id: {}", accounts.size(), customerId);
        return accounts;
    }
}