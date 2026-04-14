package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAO extends AbstractMySQLDAO implements ITransactionDAO {

    private static final Logger LOGGER = LogManager.getLogger(TransactionDAO.class);

    @Override
    public Transaction save(Transaction transaction) {
        String sql = "INSERT INTO transactions (account_id, amount, type, created_at, status) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, transaction.getAccountId());
                ps.setBigDecimal(2, transaction.getAmount());
                ps.setString(3, transaction.getType());
                ps.setTimestamp(4, Timestamp.valueOf(transaction.getCreatedAt()));
                ps.setString(5, transaction.getStatus());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        transaction.setTransactionId(keys.getLong(1));
                    }
                }
            }
            return transaction;
        } catch (SQLException e) {
            LOGGER.error("Error saving transaction", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transaction by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions";
        Connection con = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all transactions", e);
        } finally {
            releaseConnection(con);
        }
        return transactions;
    }

    @Override
    public boolean update(Transaction transaction) {
        String sql = "UPDATE transactions SET account_id = ?, amount = ?, type = ?, created_at = ?, status = ? WHERE transaction_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, transaction.getAccountId());
                ps.setBigDecimal(2, transaction.getAmount());
                ps.setString(3, transaction.getType());
                ps.setTimestamp(4, Timestamp.valueOf(transaction.getCreatedAt()));
                ps.setString(5, transaction.getStatus());
                ps.setLong(6, transaction.getTransactionId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating transaction", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting transaction", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        String sql = "SELECT * FROM transactions WHERE account_id = ?";
        Connection con = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, accountId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transactions.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transactions by account id", e);
        } finally {
            releaseConnection(con);
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByStatus(String status) {
        String sql = "SELECT * FROM transactions WHERE status = ?";
        Connection con = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, status);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transactions.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transactions by status", e);
        } finally {
            releaseConnection(con);
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByType(String type) {
        String sql = "SELECT * FROM transactions WHERE type = ?";
        Connection con = null;
        List<Transaction> transactions = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, type);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transactions.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transactions by type", e);
        } finally {
            releaseConnection(con);
        }
        return transactions;
    }

    private Transaction mapRow(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getLong("transaction_id"));
        transaction.setAccountId(rs.getLong("account_id"));
        transaction.setAmount(rs.getBigDecimal("amount"));
        transaction.setType(rs.getString("type"));
        transaction.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        transaction.setStatus(rs.getString("status"));
        return transaction;
    }
}