package com.solvd.bankingapp.models;

import java.time.LocalDate;

public class Card {

    private Long      cardId;
    private String    cardNumber;
    private String    cardType;
    private LocalDate expiryDate;
    private String    status;
    private Long      accountId;

    public Card() {}

    public Card(Long cardId, String cardNumber, String cardType,
                LocalDate expiryDate, String status, Long accountId) {
        this.cardId     = cardId;
        this.cardNumber = cardNumber;
        this.cardType   = cardType;
        this.expiryDate = expiryDate;
        this.status     = status;
        this.accountId  = accountId;
    }

    public Long      getCardId()                      { return cardId; }
    public void      setCardId(Long cardId)         { this.cardId = cardId; }
    public String    getCardNumber()                      { return cardNumber; }
    public void      setCardNumber(String cardNumber)         { this.cardNumber = cardNumber; }
    public String    getCardType()                        { return cardType; }
    public void      setCardType(String cardType)             { this.cardType = cardType; }
    public LocalDate getExpiryDate()                      { return expiryDate; }
    public void      setExpiryDate(LocalDate expiryDate)      { this.expiryDate = expiryDate; }
    public String    getStatus()                          { return status; }
    public void      setStatus(String status)                 { this.status = status; }
    public Long      getAccountId()                       { return accountId; }
    public void      setAccountId(Long accountId)             { this.accountId = accountId; }
}