package com.solvd.bankingapp.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanPayment {

    private Long       paymentId;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String     paymentMethod;
    private String     status;
    private Long       loanId;

    public LoanPayment() {}

    public LoanPayment(Long paymentId, BigDecimal amount, LocalDate paymentDate,
                       String paymentMethod, String status, Long loanId) {
        this.paymentId     = paymentId;
        this.amount        = amount;
        this.paymentDate   = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status        = status;
        this.loanId        = loanId;
    }

    public Long       getPaymentId()                              { return paymentId; }
    public void       setPaymentId(Long paymentId)                      { this.paymentId = paymentId; }
    public BigDecimal getAmount()                            { return amount; }
    public void       setAmount(BigDecimal amount)               { this.amount = amount; }
    public LocalDate  getPaymentDate()                         { return paymentDate; }
    public void       setPaymentDate(LocalDate paymentDate)       { this.paymentDate = paymentDate; }
    public String     getPaymentMethod()                       { return paymentMethod; }
    public void       setPaymentMethod(String paymentMethod)      { this.paymentMethod = paymentMethod; }
    public String     getStatus()                              { return status; }
    public void       setStatus(String status)                    { this.status = status; }
    public Long       getLoanId()                              { return loanId; }
    public void       setLoanId(Long loanId)                      { this.loanId = loanId; }
}