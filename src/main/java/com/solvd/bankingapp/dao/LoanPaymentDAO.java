package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.LoanPayment;
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

public class LoanPaymentDAO extends AbstractMySQLDAO implements ILoanPaymentDAO {

    private static final Logger LOGGER = LogManager.getLogger(LoanPaymentDAO.class);

    @Override
    public LoanPayment save(LoanPayment payment) {
        String sql = "INSERT INTO loan_payments (amount, payment_date, payment_method, status, loan_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setBigDecimal(1, payment.getAmount());
                ps.setDate(2, Date.valueOf(payment.getPaymentDate()));
                ps.setString(3, payment.getPaymentMethod());
                ps.setString(4, payment.getStatus());
                ps.setLong(5, payment.getLoanId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        payment.setPaymentId(keys.getLong(1));
                    }
                }
            }
            return payment;
        } catch (SQLException e) {
            LOGGER.error("Error saving loan payment", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<LoanPayment> findById(Long id) {
        String sql = "SELECT * FROM loan_payments WHERE payment_id = ?";
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
            LOGGER.error("Error finding loan payment by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<LoanPayment> findAll() {
        String sql = "SELECT * FROM loan_payments";
        Connection con = null;
        List<LoanPayment> payments = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all loan payments", e);
        } finally {
            releaseConnection(con);
        }
        return payments;
    }

    @Override
    public boolean update(LoanPayment payment) {
        String sql = "UPDATE loan_payments SET amount = ?, payment_date = ?, payment_method = ?, status = ?, loan_id = ? WHERE payment_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setBigDecimal(1, payment.getAmount());
                ps.setDate(2, Date.valueOf(payment.getPaymentDate()));
                ps.setString(3, payment.getPaymentMethod());
                ps.setString(4, payment.getStatus());
                ps.setLong(5, payment.getLoanId());
                ps.setLong(6, payment.getPaymentId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating loan payment", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM loan_payments WHERE payment_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting loan payment", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<LoanPayment> findByLoanId(Long loanId) {
        String sql = "SELECT * FROM loan_payments WHERE loan_id = ?";
        Connection con = null;
        List<LoanPayment> payments = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, loanId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        payments.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding loan payments by loan id", e);
        } finally {
            releaseConnection(con);
        }
        return payments;
    }

    @Override
    public List<LoanPayment> findByStatus(String status) {
        String sql = "SELECT * FROM loan_payments WHERE status = ?";
        Connection con = null;
        List<LoanPayment> payments = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, status);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        payments.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding loan payments by status", e);
        } finally {
            releaseConnection(con);
        }
        return payments;
    }

    private LoanPayment mapRow(ResultSet rs) throws SQLException {
        LoanPayment payment = new LoanPayment();
        payment.setPaymentId(rs.getLong("payment_id"));
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
        payment.setPaymentMethod(rs.getString("payment_method"));
        payment.setStatus(rs.getString("status"));
        payment.setLoanId(rs.getLong("loan_id"));
        return payment;
    }
}