package com.solvd.bankingapp.dao.factory;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;
import com.solvd.bankingapp.mybatis.MyBatisAccountDAO;
import com.solvd.bankingapp.mybatis.MyBatisCustomerDAO;

public class MyBatisDAOFactory extends DAOFactory {

    @Override
    public IAccountDAO getAccountDAO() {
        return new MyBatisAccountDAO();
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return new MyBatisCustomerDAO();
    }

    @Override
    public ITransactionDAO getTransactionDAO() {
        return new MyBatisTransactionDAO();
    }
}