package com.solvd.bankingapp.dao;
import java.sql.Connection;

public abstract class AbstractMySQLDAO {

    protected Connection getConnection() {
        try {
            return ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for a connection", e);
        }
    }

    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}