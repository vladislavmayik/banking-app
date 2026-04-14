package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Reward;

import java.util.List;
import java.util.Optional;

public interface IRewardDAO extends IBaseDAO<Reward> {

    Optional<Reward> findByAccountId(Long accountId);

    List<Reward> findByLoyaltyLevel(String loyaltyLevel);
}