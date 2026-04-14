package com.solvd.bankingapp.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long          transactionId;
    private Long          accountId;
    private BigDecimal    amount;
    private String        type;
    private LocalDateTime createdAt;
    private String        status;

    public Transaction() {}

    public Transaction(Long transactionId, Long accountId, BigDecimal amount, String type, LocalDateTime createdAt, String status) {
        this.transactionId = transactionId;
        this.accountId     = accountId;
        this.amount        = amount;
        this.type          = type;
        this.createdAt     = createdAt;
        this.status        = status;
    }

    public Long         getTransactionId() { return transactionId; }
    public void         setTransactionId(Long transactionId) { this.transactionId = transactionId; }
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