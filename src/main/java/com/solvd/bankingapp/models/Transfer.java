package com.solvd.bankingapp.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer {

    private Long          transferId;
    private BigDecimal    amount;
    private LocalDateTime createdAt;
    private String        status;
    private Long          fromAccountId;
    private Long          toAccountId;


    public Transfer() {}

    public Transfer(Long transferId, BigDecimal amount, LocalDateTime createdAt, String status, Long fromAccountId,
                    Long toAccountId) {
        this.transferId    = transferId;
        this.amount        = amount;
        this.createdAt     = createdAt;
        this.status        = status;
        this.fromAccountId = fromAccountId;
        this.toAccountId   = toAccountId;
    }

    public Long          getTransferId()                          { return transferId; }
    public void          setTransferId(Long transferId)                { this.transferId = transferId; }
    public BigDecimal    getAmount()                            { return amount; }
    public void          setAmount(BigDecimal amount)               { this.amount = amount; }
    public LocalDateTime getCreatedAt()                          { return createdAt; }
    public void          setCreatedAt(LocalDateTime createdAt)       { this.createdAt = createdAt; }
    public String        getStatus()                              { return status; }
    public void          setStatus(String status)                    { this.status = status; }
    public Long          getFromAccountId()                      { return fromAccountId; }
    public void          setFromAccountId(Long fromAccountId)        { this.fromAccountId = fromAccountId; }
    public Long          getToAccountId()                        { return toAccountId; }
    public void          setToAccountId(Long toAccountId)            { this.toAccountId = toAccountId; }
}