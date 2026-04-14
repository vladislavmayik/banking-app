package com.solvd.bankingapp.models;

import java.math.BigDecimal;

public class Account{

    private Long       accountId;
    private Long       customerId;
    private Long       currencyId;
    private String     accountNumber;
    private String     accountType;
    private BigDecimal balance;
    private String     status;

    public Account() {}

    public Account(Long accountId, Long customerId, Long currencyId, String accountNumber,
                   String accountType, BigDecimal balance, String status) {
        this.accountId     = accountId;
        this.customerId    = customerId;
        this.currencyId    = currencyId;
        this.accountNumber = accountNumber;
        this.accountType   = accountType;
        this.balance       = balance;
        this.status        = status;
    }

    public Long       getAccountId()                          { return accountId; }
    public void       setAccountId(Long accountId)               { this.accountId = accountId; }
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
