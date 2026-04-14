package com.solvd.bankingapp.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {

    private Long       loanId;
    private BigDecimal principal;
    private BigDecimal interestRate;
    private LocalDate startDate;
    private String     status;
    private Long       customerId;
    private Long       currencyId;

    public Loan() {}

    public Loan(Long loanId, BigDecimal principal, BigDecimal interestRate,
                LocalDate startDate, String status,
                Long customerId, Long currencyId) {
        this.loanId       = loanId;
        this.principal    = principal;
        this.interestRate = interestRate;
        this.startDate    = startDate;
        this.status       = status;
        this.customerId   = customerId;
        this.currencyId   = currencyId;
    }

    public Long       getLoanId()                         { return loanId; }
    public void       setLoanId(Long loanId)              { this.loanId = loanId; }
    public BigDecimal getPrincipal()                          { return principal; }
    public void       setPrincipal(BigDecimal principal)          { this.principal = principal; }
    public BigDecimal getInterestRate()                       { return interestRate; }
    public void       setInterestRate(BigDecimal interestRate)    { this.interestRate = interestRate; }
    public LocalDate  getStartDate()                          { return startDate; }
    public void       setStartDate(LocalDate startDate)          { this.startDate = startDate; }
    public String     getStatus()                             { return status; }
    public void       setStatus(String status)                    { this.status = status; }
    public Long       getCustomerId()                         { return customerId; }
    public void       setCustomerId(Long customerId)              { this.customerId = customerId; }
    public Long       getCurrencyId()                         { return currencyId; }
    public void       setCurrencyId(Long currencyId)              { this.currencyId = currencyId; }
}
