package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Loan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanDAO extends AbstractMySQLDAO implements ILoanDAO {

    private static final Logger LOGGER = LogManager.getLogger(LoanDAO.class);

    @Override
    public Loan save(Loan loan) {
        String sql = "INSERT INTO loans (principal, interest_rate, start_date, status, customer_id, currency_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setBigDecimal(1, loan.getPrincipal());
                ps.setBigDecimal(2, loan.getInterestRate());
                ps.setDate(3, Date.valueOf(loan.getStartDate()));
                ps.setString(4, loan.getStatus());
                ps.setLong(5, loan.getCustomerId());
                ps.setLong(6, loan.getCurrencyId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        loan.setLoanId(keys.getLong(1));
                    }
                }
            }
            return loan;
        } catch (SQLException e) {
            LOGGER.error("Error saving loan", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Loan> findById(Long id) {
        String sql = "SELECT * FROM loans WHERE loan_id = ?";
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
            LOGGER.error("Error finding loan by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Loan> findAll() {
        String sql = "SELECT * FROM loans";
        Connection con = null;
        List<Loan> loans = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    loans.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all loans", e);
        } finally {
            releaseConnection(con);
        }
        return loans;
    }

    @Override
    public boolean update(Loan loan) {
        String sql = "UPDATE loans SET principal = ?, interest_rate = ?, start_date = ?, status = ?, customer_id = ?, currency_id = ? WHERE loan_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setBigDecimal(1, loan.getPrincipal());
                ps.setBigDecimal(2, loan.getInterestRate());
                ps.setDate(3, Date.valueOf(loan.getStartDate()));
                ps.setString(4, loan.getStatus());
                ps.setLong(5, loan.getCustomerId());
                ps.setLong(6, loan.getCurrencyId());
                ps.setLong(7, loan.getLoanId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating loan", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM loans WHERE loan_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting loan", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<Loan> findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM loans WHERE customer_id = ?";
        Connection con = null;
        List<Loan> loans = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        loans.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding loans by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return loans;
    }

    @Override
    public List<Loan> findByStatus(String status) {
        String sql = "SELECT * FROM loans WHERE status = ?";
        Connection con = null;
        List<Loan> loans = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, status);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        loans.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding loans by status", e);
        } finally {
            releaseConnection(con);
        }
        return loans;
    }

    private Loan mapRow(ResultSet rs) throws SQLException {
        Loan loan = new Loan();
        loan.setLoanId(rs.getLong("loan_id"));
        loan.setPrincipal(rs.getBigDecimal("principal"));
        loan.setInterestRate(rs.getBigDecimal("interest_rate"));
        loan.setStartDate(rs.getDate("start_date").toLocalDate());
        loan.setStatus(rs.getString("status"));
        loan.setCustomerId(rs.getLong("customer_id"));
        loan.setCurrencyId(rs.getLong("currency_id"));
        return loan;
    }
}