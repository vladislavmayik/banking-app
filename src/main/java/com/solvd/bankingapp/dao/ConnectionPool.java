package com.solvd.bankingapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private static final int MAX_CONNECTIONS = 5;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final String URL      = "jdbc:mysql://localhost:3306/bankingapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private ConnectionPool() {
        pool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            pool.add(createConnection());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        LOGGER.info("A connection is being requested from the pool.");
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        LOGGER.info("Connection returned to the pool.");
        pool.offer(connection);
    }

    private Connection createConnection() {
             try {
                 return DriverManager.getConnection(URL, USERNAME, PASSWORD);
             } catch (SQLException e) {
                 throw new RuntimeException("Failed to create database connection", e);
             }
         }
}