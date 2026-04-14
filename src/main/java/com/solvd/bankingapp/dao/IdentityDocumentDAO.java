package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.IdentityDocument;
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

public class IdentityDocumentDAO extends AbstractMySQLDAO implements IIdentityDocumentDAO {

    private static final Logger LOGGER = LogManager.getLogger(IdentityDocumentDAO.class);

    @Override
    public IdentityDocument save(IdentityDocument document) {
        String sql = "INSERT INTO identity_documents (document_number, issuer_country, expiry_date, type, customer_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, document.getDocumentNumber());
                ps.setString(2, document.getIssuerCountry());
                ps.setDate(3, Date.valueOf(document.getExpiryDate()));
                ps.setString(4, document.getType());
                ps.setLong(5, document.getCustomerId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        document.setDocumentId(keys.getLong(1));
                    }
                }
            }
            return document;
        } catch (SQLException e) {
            LOGGER.error("Error saving identity document", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<IdentityDocument> findById(Long id) {
        String sql = "SELECT * FROM identity_documents WHERE document_id = ?";
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
            LOGGER.error("Error finding identity document by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<IdentityDocument> findAll() {
        String sql = "SELECT * FROM identity_documents";
        Connection con = null;
        List<IdentityDocument> documents = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    documents.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all identity documents", e);
        } finally {
            releaseConnection(con);
        }
        return documents;
    }

    @Override
    public boolean update(IdentityDocument document) {
        String sql = "UPDATE identity_documents SET document_number = ?, issuer_country = ?, expiry_date = ?, type = ?, customer_id = ? WHERE document_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, document.getDocumentNumber());
                ps.setString(2, document.getIssuerCountry());
                ps.setDate(3, Date.valueOf(document.getExpiryDate()));
                ps.setString(4, document.getType());
                ps.setLong(5, document.getCustomerId());
                ps.setLong(6, document.getDocumentId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating identity document", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM identity_documents WHERE document_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting identity document", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<IdentityDocument> findByDocumentNumber(String documentNumber) {
        String sql = "SELECT * FROM identity_documents WHERE document_number = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, documentNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding identity document by document number", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<IdentityDocument> findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM identity_documents WHERE customer_id = ?";
        Connection con = null;
        List<IdentityDocument> documents = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        documents.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding identity documents by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return documents;
    }

    private IdentityDocument mapRow(ResultSet rs) throws SQLException {
        IdentityDocument document = new IdentityDocument();
        document.setDocumentId(rs.getLong("document_id"));
        document.setDocumentNumber(rs.getString("document_number"));
        document.setIssuerCountry(rs.getString("issuer_country"));
        document.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        document.setType(rs.getString("type"));
        document.setCustomerId(rs.getLong("customer_id"));
        return document;
    }
}