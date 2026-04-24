package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "accountId")
    private Long accountId;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlElement(name = "type")
    private String type;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;

    @XmlElement(name = "status")
    private String status;

    public Transaction() {}

    public Transaction(Long id, Long accountId, BigDecimal amount, String type, LocalDateTime createdAt, String status) {
        this.id = id;
        this.accountId     = accountId;
        this.amount        = amount;
        this.type          = type;
        this.createdAt     = createdAt;
        this.status        = status;
    }

    public Long         getTransactionId() { return id; }
    public void         setTransactionId(Long id) { this.id = id; }
    public Long          getAccountId() { return accountId; }
    public void          setAccountId(Long accountId) { this.accountId = accountId; }
    public BigDecimal    getAmount()    { return amount; }
    public void          setAmount(BigDecimal amount) { this.amount = amount; }
    public String        getType()      { return type; }
    public void          setType(String type) { this.type = type; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void          setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String        getStatus()    { return status; }
    public void          setStatus(String status) { this.status = status; }
}