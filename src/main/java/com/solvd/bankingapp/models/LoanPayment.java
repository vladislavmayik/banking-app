package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
@XmlRootElement(name = "loanPayment")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoanPayment {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "paymentDate")
    private LocalDate paymentDate;

    @XmlElement(name = "paymentMethod")
    private String paymentMethod;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "loanId")
    private Long loanId;

    public LoanPayment() {}

    public LoanPayment(Long id, BigDecimal amount, LocalDate paymentDate,
                       String paymentMethod, String status, Long loanId) {
        this.id     = id;
        this.amount        = amount;
        this.paymentDate   = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status        = status;
        this.loanId        = loanId;
    }

    public Long       getPaymentId()                              { return id; }
    public void       setPaymentId(Long id)                      { this.id = id; }
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