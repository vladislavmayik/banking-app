package com.solvd.bankingapp.dao.factory;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;
import com.solvd.bankingapp.dao.impl.AccountDAO;
import com.solvd.bankingapp.dao.impl.CustomerDAO;
import com.solvd.bankingapp.dao.impl.TransactionDAO;

public class JdbcDAOFactory extends DAOFactory {

    @Override
    public IAccountDAO getAccountDAO() {
        return new AccountDAO();
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return new CustomerDAO();
    }

    @Override
    public ITransactionDAO getTransactionDAO() {
        return new TransactionDAO();
    }
}