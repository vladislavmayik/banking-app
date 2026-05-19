package com.solvd.bankingapp.dao.factory;

import com.solvd.bankingapp.dao.interfaces.IAccountDAO;
import com.solvd.bankingapp.dao.interfaces.ICustomerDAO;
import com.solvd.bankingapp.dao.interfaces.ITransactionDAO;
import org.apache.ibatis.session.SqlSession;

public class MyBatisDAOFactory extends DAOFactory {

    @Override
    public IAccountDAO getAccountDAO() {
        SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
        return session.getMapper(IAccountDAO.class);
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
        return session.getMapper(ICustomerDAO.class);
    }

    @Override
    public ITransactionDAO getTransactionDAO() {
        SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
        return session.getMapper(ITransactionDAO.class);
    }
}