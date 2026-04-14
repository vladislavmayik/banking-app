package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDAO extends AbstractMySQLDAO implements ICurrencyDAO {

    private static final Logger LOGGER = LogManager.getLogger(CurrencyDAO.class);

    @Override
    public Currency save(Currency currency) {
        String sql = "INSERT INTO currencies (currency_code, name, symbol) VALUES (?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, currency.getCurrencyCode());
                ps.setString(2, currency.getName());
                ps.setString(3, currency.getSymbol());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        currency.setCurrencyId(keys.getLong(1));
                    }
                }
            }
            return currency;
        } catch (SQLException e) {
            LOGGER.error("Error saving currency", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Currency> findById(Long id) {
        String sql = "SELECT * FROM currencies WHERE currency_id = ?";
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
            LOGGER.error("Error finding currency by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Currency> findAll() {
        String sql = "SELECT * FROM currencies";
        Connection con = null;
        List<Currency> currencies = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    currencies.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all currencies", e);
        } finally {
            releaseConnection(con);
        }
        return currencies;
    }

    @Override
    public boolean update(Currency currency) {
        String sql = "UPDATE currencies SET currency_code = ?, name = ?, symbol = ? WHERE currency_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, currency.getCurrencyCode());
                ps.setString(2, currency.getName());
                ps.setString(3, currency.getSymbol());
                ps.setLong(4, currency.getCurrencyId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating currency", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM currencies WHERE currency_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting currency", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<Currency> findByCurrencyCode(String currencyCode) {
        String sql = "SELECT * FROM currencies WHERE currency_code = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, currencyCode);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding currency by code", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Currency> findByName(String name) {
        String sql = "SELECT * FROM currencies WHERE name = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding currency by name", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    private Currency mapRow(ResultSet rs) throws SQLException {
        return new Currency(
                rs.getLong("currency_id"),
                rs.getString("currency_code"),
                rs.getString("name"),
                rs.getString("symbol")
        );
    }
}