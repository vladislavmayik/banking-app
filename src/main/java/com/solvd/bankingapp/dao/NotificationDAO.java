package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Notification;
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

public class NotificationDAO extends AbstractMySQLDAO implements INotificationDAO {

    private static final Logger LOGGER = LogManager.getLogger(NotificationDAO.class);

    @Override
    public Notification save(Notification notification) {
        String sql = "INSERT INTO notifications (type, message, sent_at, is_read, customer_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, notification.getType());
                ps.setString(2, notification.getMessage());
                ps.setTimestamp(3, Timestamp.valueOf(notification.getSentAt()));
                ps.setBoolean(4, notification.isRead());
                ps.setLong(5, notification.getCustomerId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        notification.setNotificationId(keys.getLong(1));
                    }
                }
            }
            return notification;
        } catch (SQLException e) {
            LOGGER.error("Error saving notification", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Notification> findById(Long id) {
        String sql = "SELECT * FROM notifications WHERE notification_id = ?";
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
            LOGGER.error("Error finding notification by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Notification> findAll() {
        String sql = "SELECT * FROM notifications";
        Connection con = null;
        List<Notification> notifications = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    notifications.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all notifications", e);
        } finally {
            releaseConnection(con);
        }
        return notifications;
    }

    @Override
    public boolean update(Notification notification) {
        String sql = "UPDATE notifications SET type = ?, message = ?, sent_at = ?, is_read = ?, customer_id = ? WHERE notification_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, notification.getType());
                ps.setString(2, notification.getMessage());
                ps.setTimestamp(3, Timestamp.valueOf(notification.getSentAt()));
                ps.setBoolean(4, notification.isRead());
                ps.setLong(5, notification.getCustomerId());
                ps.setLong(6, notification.getNotificationId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating notification", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM notifications WHERE notification_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting notification", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public List<Notification> findByCustomerId(Long customerId) {
        String sql = "SELECT * FROM notifications WHERE customer_id = ?";
        Connection con = null;
        List<Notification> notifications = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        notifications.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding notifications by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return notifications;
    }

    @Override
    public List<Notification> findUnreadByCustomerId(Long customerId) {
        String sql = "SELECT * FROM notifications WHERE customer_id = ? AND is_read = false";
        Connection con = null;
        List<Notification> notifications = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, customerId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        notifications.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding unread notifications by customer id", e);
        } finally {
            releaseConnection(con);
        }
        return notifications;
    }

    private Notification mapRow(ResultSet rs) throws SQLException {
        Notification notification = new Notification();
        notification.setNotificationId(rs.getLong("notification_id"));
        notification.setType(rs.getString("type"));
        notification.setMessage(rs.getString("message"));
        notification.setSentAt(rs.getTimestamp("sent_at").toLocalDateTime());
        notification.setRead(rs.getBoolean("is_read"));
        notification.setCustomerId(rs.getLong("customer_id"));
        return notification;
    }
}