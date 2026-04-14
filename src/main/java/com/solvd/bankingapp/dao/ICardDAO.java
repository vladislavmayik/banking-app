package com.solvd.bankingapp.dao;

import com.solvd.bankingapp.models.Card;

import java.util.List;
import java.util.Optional;

public interface ICardDAO extends IBaseDAO<Card> {

    Optional<Card> findByCardNumber(String cardNumber);

    List<Card> findByAccountId(Long accountId);
}