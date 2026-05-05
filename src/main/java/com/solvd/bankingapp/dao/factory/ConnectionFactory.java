package com.solvd.bankingapp.dao.factory;

import com.solvd.bankingapp.mybatis.AccountMapper;
import com.solvd.bankingapp.mybatis.CustomerMapper;
import com.solvd.bankingapp.mybatis.TransactionMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class ConnectionFactory {

    private static SqlSessionFactory factory;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
        } catch (IOException e) {
            LOGGER.error("Failed to initialize MyBatis connection factory", e);
        }
        factory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }

    public static AccountMapper getAccountMapper() {
        try (SqlSession session = factory.openSession(true)) {
            return session.getMapper(AccountMapper.class);
        }
    }

    public static CustomerMapper getCustomerMapper() {
        try (SqlSession session = factory.openSession(true)) {
            return session.getMapper(CustomerMapper.class);
        }
    }

    public static TransactionMapper getTransactionMapper() {
        try (SqlSession session = factory.openSession(true)) {
            return session.getMapper(TransactionMapper.class);
        }
    }
}