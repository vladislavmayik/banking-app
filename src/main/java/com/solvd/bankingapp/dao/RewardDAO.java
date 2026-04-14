package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Reward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RewardDAO extends AbstractMySQLDAO implements IRewardDAO {

    private static final Logger LOGGER = LogManager.getLogger(RewardDAO.class);

    @Override
    public Reward save(Reward reward) {
        String sql = "INSERT INTO rewards (points, loyalty_level, account_id) VALUES (?, ?, ?)";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, reward.getPoints());
                ps.setString(2, reward.getLoyaltyLevel());
                ps.setLong(3, reward.getAccountId());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        reward.setRewardId(keys.getLong(1));
                    }
                }
            }
            return reward;
        } catch (SQLException e) {
            LOGGER.error("Error saving reward", e);
        } finally {
            releaseConnection(con);
        }
        return null;
    }

    @Override
    public Optional<Reward> findById(Long id) {
        String sql = "SELECT * FROM rewards WHERE reward_id = ?";
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
            LOGGER.error("Error finding reward by id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Reward> findAll() {
        String sql = "SELECT * FROM rewards";
        Connection con = null;
        List<Reward> rewards = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rewards.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding all rewards", e);
        } finally {
            releaseConnection(con);
        }
        return rewards;
    }

    @Override
    public boolean update(Reward reward) {
        String sql = "UPDATE rewards SET points = ?, loyalty_level = ?, account_id = ? WHERE reward_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, reward.getPoints());
                ps.setString(2, reward.getLoyaltyLevel());
                ps.setLong(3, reward.getAccountId());
                ps.setLong(4, reward.getRewardId());
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating reward", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM rewards WHERE reward_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Error deleting reward", e);
        } finally {
            releaseConnection(con);
        }
        return false;
    }

    @Override
    public Optional<Reward> findByAccountId(Long accountId) {
        String sql = "SELECT * FROM rewards WHERE account_id = ?";
        Connection con = null;
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setLong(1, accountId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding reward by account id", e);
        } finally {
            releaseConnection(con);
        }
        return Optional.empty();
    }

    @Override
    public List<Reward> findByLoyaltyLevel(String loyaltyLevel) {
        String sql = "SELECT * FROM rewards WHERE loyalty_level = ?";
        Connection con = null;
        List<Reward> rewards = new ArrayList<>();
        try {
            con = getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, loyaltyLevel);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        rewards.add(mapRow(rs));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error finding rewards by loyalty level", e);
        } finally {
            releaseConnection(con);
        }
        return rewards;
    }

    private Reward mapRow(ResultSet rs) throws SQLException {
        Reward reward = new Reward();
        reward.setRewardId(rs.getLong("reward_id"));
        reward.setPoints(rs.getInt("points"));
        reward.setLoyaltyLevel(rs.getString("loyalty_level"));
        reward.setAccountId(rs.getLong("account_id"));
        return reward;
    }
}