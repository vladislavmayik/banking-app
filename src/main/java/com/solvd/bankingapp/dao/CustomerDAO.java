package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Customer;
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

public class CustomerDAO extends AbstractMySQLDAO implements ICustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (full_name, email, phone, national_id, date_of_birth) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, customer.getFullName());
                ps.setString(2, customer.getEmail());
                ps.setString(3, customer.getPhone());
                ps.setString(4, customer.getNationalId());
                ps.setDate(5, Date.valueOf(customer.getDateOfBirth()));
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        customer.setCustomerId(keys.getLong(1));
                    }
                }
            }
            return customer;
        } catch (SQLException e) {
            LOGGER.error("Error saving customer", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
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
            LOGGER.error("Error finding customer by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";
        Connection con = null;
        List<Customer> customers = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customers.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all customers", e);
        } finally {
            releaseConnection(con);
        }
        return customers;
    }

    @Override
    public boolean update(Customer customer) {
        String sql = "UPDATE customers SET full_name = ?, email = ?, phone = ?, national_id = ?, date_of_birth = ? WHERE customer_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, customer.getFullName());
                ps.setString(2, customer.getEmail());
                ps.setString(3, customer.getPhone());
                ps.setString(4, customer.getNationalId());
                ps.setDate(5, Date.valueOf(customer.getDateOfBirth()));
                ps.setLong(6, customer.getCustomerId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating customer", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting customer", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding customer by email", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByNationalId(String nationalId) {
        String sql = "SELECT * FROM customers WHERE national_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, nationalId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding customer by national id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        String sql = "SELECT * FROM customers WHERE phone = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, phone);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding customer by phone", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(rs.getLong("customer_id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        customer.setNationalId(rs.getString("national_id"));
        customer.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        return customer;
    }
}