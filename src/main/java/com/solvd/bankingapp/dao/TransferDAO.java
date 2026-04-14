package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Transfer;
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

public class TransferDAO extends AbstractMySQLDAO implements ITransferDAO {

    private static final Logger LOGGER = LogManager.getLogger(TransferDAO.class);

    @Override
    public Transfer save(Transfer transfer) {
        String sql = "INSERT INTO transfers (amount, created_at, status, from_account_id, to_account_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setBigDecimal(1, transfer.getAmount());
                ps.setTimestamp(2, Timestamp.valueOf(transfer.getCreatedAt()));
                ps.setString(3, transfer.getStatus());
                ps.setLong(4, transfer.getFromAccountId());
                ps.setLong(5, transfer.getToAccountId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        transfer.setTransferId(keys.getLong(1));
                    }
                }
            }
            return transfer;
        } catch (SQLException e) {
            LOGGER.error("Error saving transfer", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";
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
            LOGGER.error("Error finding transfer by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Transfer> findAll() {
        String sql = "SELECT * FROM transfers";
        Connection con = null;
        List<Transfer> transfers = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transfers.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all transfers", e);
        } finally {
            releaseConnection(con);
        }
        return transfers;
    }

    @Override
    public boolean update(Transfer transfer) {
        String sql = "UPDATE transfers SET amount = ?, created_at = ?, status = ?, from_account_id = ?, to_account_id = ? WHERE transfer_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setBigDecimal(1, transfer.getAmount());
                ps.setTimestamp(2, Timestamp.valueOf(transfer.getCreatedAt()));
                ps.setString(3, transfer.getStatus());
                ps.setLong(4, transfer.getFromAccountId());
                ps.setLong(5, transfer.getToAccountId());
                ps.setLong(6, transfer.getTransferId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating transfer", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM transfers WHERE transfer_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting transfer", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<Transfer> findByFromAccountId(Long fromAccountId) {
        String sql = "SELECT * FROM transfers WHERE from_account_id = ?";
        Connection con = null;
        List<Transfer> transfers = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, fromAccountId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transfers.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transfers by from account id", e);
        } finally {
            releaseConnection(con);
        }
        return transfers;
    }

    @Override
    public List<Transfer> findByToAccountId(Long toAccountId) {
        String sql = "SELECT * FROM transfers WHERE to_account_id = ?";
        Connection con = null;
        List<Transfer> transfers = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, toAccountId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transfers.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transfers by to account id", e);
        } finally {
            releaseConnection(con);
        }
        return transfers;
    }

    @Override
    public List<Transfer> findByStatus(String status) {
        String sql = "SELECT * FROM transfers WHERE status = ?";
        Connection con = null;
        List<Transfer> transfers = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, status);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        transfers.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding transfers by status", e);
        } finally {
            releaseConnection(con);
        }
        return transfers;
    }

    private Transfer mapRow(ResultSet rs) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getLong("transfer_id"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        transfer.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        transfer.setStatus(rs.getString("status"));
        transfer.setFromAccountId(rs.getLong("from_account_id"));
        transfer.setToAccountId(rs.getLong("to_account_id"));
        return transfer;
    }
}