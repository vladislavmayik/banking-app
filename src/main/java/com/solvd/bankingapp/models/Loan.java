package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "loan")
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "principal")
    private BigDecimal principal;

    @XmlElement(name = "interestRate")
    private BigDecimal interestRate;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "startDate")
    private LocalDate startDate;

    @XmlElement(name = "status")
    private String status;

    @XmlElement(name = "customerId")
    private Long customerId;

    @XmlElement(name = "currencyId")
    private Long currencyId;

    public Loan() {}

    public Loan(Long id, BigDecimal principal, BigDecimal interestRate,
                LocalDate startDate, String status,
                Long customerId, Long currencyId) {
        this.id       = id;
        this.principal    = principal;
        this.interestRate = interestRate;
        this.startDate    = startDate;
        this.status       = status;
        this.customerId   = customerId;
        this.currencyId   = currencyId;
    }

    public Long       getLoanId()                         { return id; }
    public void       setLoanId(Long id)              { this.id = id; }
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
