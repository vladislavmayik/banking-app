package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO extends AbstractMySQLDAO implements IAccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(AccountDAO.class);

    @Override
    public Account save(Account account) {
        String sql = "INSERT INTO accounts (customer_id, currency_id, account_number, account_type, balance, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, account.getCustomerId());
                ps.setLong(2, account.getCurrencyId());
                ps.setString(3, account.getAccountNumber());
                ps.setString(4, account.getAccountType());
                ps.setBigDecimal(5, account.getBalance());
                ps.setString(6, account.getStatus());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        account.setAccountId(keys.getLong(1));
                    }
                }
            }
            return account;
        } catch (SQLException e) {
            LOGGER.error("Error saving account", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
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
            LOGGER.error("Error finding account by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        Connection con = null;
        List<Account> accounts = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all accounts", e);
        } finally {
            releaseConnection(con);
        }
        return accounts;
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE accounts SET customer_id = ?, currency_id = ?, account_number = ?, account_type = ?, balance = ?, status = ? WHERE account_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, account.getCustomerId());
                ps.setLong(2, account.getCurrencyId());
                ps.setString(3, account.getAccountNumber());
                ps.setString(4, account.getAccountType());
                ps.setBigDecimal(5, account.getBalance());
                ps.setString(6, account.getStatus());
                ps.setLong(7, account.getAccountId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating account", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting account by id", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, accountNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding account by account number", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM accounts WHERE customer_id = ?";
        Connection con = null;
        List<Account> accounts = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        accounts.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding accounts by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return accounts;
    }

    private Account mapRow(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getLong("account_id"));
        account.setCustomerId(rs.getLong("customer_id"));
        account.setCurrencyId(rs.getLong("currency_id"));
        account.setAccountNumber(rs.getString("account_number"));
        account.setAccountType(rs.getString("account_type"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setStatus(rs.getString("status"));
        return account;
    }
}