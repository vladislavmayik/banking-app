package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "transfer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transfer {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "fromAccountId")
    private Long fromAccountId;

    @XmlElement(name = "toAccountId")
    private Long toAccountId;


    public Transfer() {}

    public Transfer(Long id, BigDecimal amount, LocalDateTime createdAt, String status, Long fromAccountId,
                    Long toAccountId) {
        this.id    = id;
        this.amount        = amount;
        this.createdAt     = createdAt;
        this.status        = status;
        this.fromAccountId = fromAccountId;
        this.toAccountId   = toAccountId;
    }

    public Long          getTransferId()                          { return id; }
    public void          setTransferId(Long id)                { this.id = id; }
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