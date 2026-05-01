package com.solvd.bankingapp.decorator;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.models.Account;

import java.util.List;
import java.util.Optional;

public abstract class AccountDAODecorator implements IAccountDAO {

    protected final IAccountDAO accountDAO;

    public AccountDAODecorator(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account save(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountDAO.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public boolean update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public boolean deleteById(Long id) {
        return accountDAO.deleteById(id);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountDAO.findByAccountNumber(accountNumber);
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        return accountDAO.findByCustomerId(customerId);
    }
}