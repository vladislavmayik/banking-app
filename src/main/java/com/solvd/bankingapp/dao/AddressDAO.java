package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDAO extends AbstractMySQLDAO implements IAddressDAO {

    private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);

    @Override
    public Address save(Address address) {
        String sql = "INSERT INTO addresses (street, city, country, postal_code, customer_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, address.getStreet());
                ps.setString(2, address.getCity());
                ps.setString(3, address.getCountry());
                ps.setString(4, address.getPostalCode());
                ps.setLong(5, address.getCustomerId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        address.setAddressId(keys.getLong(1));
                    }
                }
            }
            return address;
        } catch (SQLException e) {
            LOGGER.error("Error saving address", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Address> findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE address_id = ?";
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
            LOGGER.error("Error finding address by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Address> findAll() {
        String sql = "SELECT * FROM addresses";
        Connection con = null;
        List<Address> addresses = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    addresses.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all addresses", e);
        } finally {
            releaseConnection(con);
        }
        return addresses;
    }

    @Override
    public boolean update(Address address) {
        String sql = "UPDATE addresses SET street = ?, city = ?, country = ?, postal_code = ?, customer_id = ? WHERE address_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, address.getStreet());
                ps.setString(2, address.getCity());
                ps.setString(3, address.getCountry());
                ps.setString(4, address.getPostalCode());
                ps.setLong(5, address.getCustomerId());
                ps.setLong(6, address.getAddressId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating address", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM addresses WHERE address_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting address", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM addresses WHERE customer_id = ?";
        Connection con = null;
        List<Address> addresses = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        addresses.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding addresses by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return addresses;
    }

    @Override
    public List<Address> findByCity(String city) {
        String sql = "SELECT * FROM addresses WHERE city = ?";
        Connection con = null;
        List<Address> addresses = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, city);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        addresses.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding addresses by city", e);
        } finally {
            releaseConnection(con);
        }
        return addresses;
    }

    @Override
    public List<Address> findByCountry(String country) {
        String sql = "SELECT * FROM addresses WHERE country = ?";
        Connection con = null;
        List<Address> addresses = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, country);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        addresses.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding addresses by country", e);
        } finally {
            releaseConnection(con);
        }
        return addresses;
    }

    private Address mapRow(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getLong("address_id"));
        address.setStreet(rs.getString("street"));
        address.setCity(rs.getString("city"));
        address.setCountry(rs.getString("country"));
        address.setPostalCode(rs.getString("postal_code"));
        address.setCustomerId(rs.getLong("customer_id"));
        return address;
    }
}