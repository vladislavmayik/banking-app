package com.solvd.bankingapp.dao.factory;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;

public abstract class DAOFactory {

    public static final int JDBC = 1;
    public static final int MYBATIS = 2;

    public abstract IAccountDAO getAccountDAO();
    public abstract ICustomerDAO getCustomerDAO();
    public abstract ITransactionDAO getTransactionDAO();

    public static DAOFactory getInstance(int type) {
        switch (type) {
            case JDBC: return new JdbcDAOFactory();
            case MYBATIS: return new MyBatisDAOFactory();
            default: throw new IllegalArgumentException("Unknown factory type: " + type);
        }
    }
}