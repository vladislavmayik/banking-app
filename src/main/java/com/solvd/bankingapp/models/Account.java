package com.solvd.bankingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account{

    @XmlElement(name = "accountId")
    @JsonProperty("accountId")
    private Long id;

    @XmlElement(name = "customerId")
    @JsonProperty("customerId")
    private Long customerId;

    @XmlElement(name = "currencyId")
    @JsonProperty("currencyId")
    private Long currencyId;

    @XmlElement(name = "accountNumber")
    @JsonProperty("accountNumber")
    private String accountNumber;

    @XmlElement(name = "accountType")
    @JsonProperty("accountType")
    private String accountType;

    @XmlElement(name = "balance")
    @JsonProperty("balance")
    private BigDecimal balance;

    @XmlElement(name = "status")
    @JsonProperty("status")
    private String status;

    public Account() {}

    public Account(Long id, Long customerId, Long currencyId, String accountNumber,
                   String accountType, BigDecimal balance, String status) {
        this.id     = id;
        this.customerId    = customerId;
        this.currencyId    = currencyId;
        this.accountNumber = accountNumber;
        this.accountType   = accountType;
        this.balance       = balance;
        this.status        = status;
    }

    public Long       getAccountId()                          { return id; }
    public void       setAccountId(Long id)               { this.id = id; }
    public Long       getCustomerId()                          { return customerId; }
    public void       setCustomerId(Long customerId)               { this.customerId = customerId; }
    public Long       getCurrencyId()                          { return currencyId; }
    public void       setCurrencyId(Long currencyId)               { this.currencyId = currencyId; }
    public String     getAccountNumber()                       { return accountNumber; }
    public void       setAccountNumber(String accountNumber)       { this.accountNumber = accountNumber; }
    public String     getAccountType()                         { return accountType; }
    public void       setAccountType(String accountType)           { this.accountType = accountType; }
    public BigDecimal getBalance()                             { return balance; }
    public void       setBalance(BigDecimal balance)                { this.balance = balance; }
    public String     getStatus()                              { return status; }
    public void       setStatus(String status)                     { this.status = status; }
}
