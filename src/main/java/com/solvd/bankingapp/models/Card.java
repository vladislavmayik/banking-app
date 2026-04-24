package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "cardNumber")
    private String cardNumber;

    @XmlElement(name = "cardType")
    private String cardType;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "expiryDate")
    private LocalDate expiryDate;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "accountId")
    private Long accountId;

    public Card() {}

    public Card(Long id, String cardNumber, String cardType,
                LocalDate expiryDate, String status, Long accountId) {
        this.id     = id;
        this.cardNumber = cardNumber;
        this.cardType   = cardType;
        this.expiryDate = expiryDate;
        this.status     = status;
        this.accountId  = accountId;
    }

    public Long      getCardId()                      { return id; }
    public void      setCardId(Long id)         { this.id = id; }
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