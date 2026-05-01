package com.solvd.bankingapp.proxy;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AccountDAOProxy implements IAccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(AccountDAOProxy.class);

    private final IAccountDAO accountDAO;
    private final String userRole;

    public AccountDAOProxy(IAccountDAO accountDAO, String userRole) {
        this.accountDAO = accountDAO;
        this.userRole = userRole;
    }

    private void checkRole(String requiredRole) {
        if (!userRole.equalsIgnoreCase(requiredRole)) {
            LOGGER.warn("Access denied for role '{}'. Required: '{}'", userRole, requiredRole);
            throw new SecurityException("Access denied. Required role: " + requiredRole);
        }
    }

    @Override
    public Account save(Account account) {
        checkRole("ADMIN");
        LOGGER.info("Proxy: save allowed for role '{}'", userRole);
        return accountDAO.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        checkRole("USER");
        LOGGER.info("Proxy: findById allowed for role '{}'", userRole);
        return accountDAO.findById(id);
    }

    @Override
    public List<Account> findAll() {
        checkRole("ADMIN");
        LOGGER.info("Proxy: findAll allowed for role '{}'", userRole);
        return accountDAO.findAll();
    }

    @Override
    public boolean update(Account account) {
        checkRole("ADMIN");
        LOGGER.info("Proxy: update allowed for role '{}'", userRole);
        return accountDAO.update(account);
    }

    @Override
    public boolean deleteById(Long id) {
        checkRole("ADMIN");
        LOGGER.info("Proxy: deleteById allowed for role '{}'", userRole);
        return accountDAO.deleteById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        checkRole("USER");
        return accountDAO.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        checkRole("USER");
        return accountDAO.findByCustomerId(customerId);
    }
}