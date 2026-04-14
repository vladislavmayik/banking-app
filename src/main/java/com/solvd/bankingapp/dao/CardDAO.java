package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Card;
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

public class CardDAO extends AbstractMySQLDAO implements ICardDAO {

    private static final Logger LOGGER = LogManager.getLogger(CardDAO.class);

    @Override
    public Card save(Card card) {
        String sql = "INSERT INTO cards (card_number, card_type, expiry_date, status, account_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, card.getCardNumber());
                ps.setString(2, card.getCardType());
                ps.setDate(3, Date.valueOf(card.getExpiryDate()));
                ps.setString(4, card.getStatus());
                ps.setLong(5, card.getAccountId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        card.setCardId(keys.getLong(1));
                    }
                }
            }
            return card;
        } catch (SQLException e) {
            LOGGER.error("Error saving card", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Card> findById(Long id) {
        String sql = "SELECT * FROM cards WHERE card_id = ?";
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
            LOGGER.error("Error finding card by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAll() {
        String sql = "SELECT * FROM cards";
        Connection con = null;
        List<Card> cards = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all cards", e);
        } finally {
            releaseConnection(con);
        }
        return cards;
    }

    @Override
    public boolean update(Card card) {
        String sql = "UPDATE cards SET card_number = ?, card_type = ?, expiry_date = ?, status = ?, account_id = ? WHERE card_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, card.getCardNumber());
                ps.setString(2, card.getCardType());
                ps.setDate(3, Date.valueOf(card.getExpiryDate()));
                ps.setString(4, card.getStatus());
                ps.setLong(5, card.getAccountId());
                ps.setLong(6, card.getCardId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating card", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM cards WHERE card_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting card", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<Card> findByCardNumber(String cardNumber) {
        String sql = "SELECT * FROM cards WHERE card_number = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cardNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding card by card number", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findByAccountId(Long accountId) {
        String sql = "SELECT * FROM cards WHERE account_id = ?";
        Connection con = null;
        List<Card> cards = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, accountId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        cards.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding cards by account id", e);
        } finally {
            releaseConnection(con);
        }
        return cards;
    }

    private Card mapRow(ResultSet rs) throws SQLException {
        Card card = new Card();
        card.setCardId(rs.getLong("card_id"));
        card.setCardNumber(rs.getString("card_number"));
        card.setCardType(rs.getString("card_type"));
        card.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
        card.setStatus(rs.getString("status"));
        card.setAccountId(rs.getLong("account_id"));
        return card;
    }
}