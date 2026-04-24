package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {

    @XmlElement(name = "customerId")
    private Long id;

    @XmlElement(name = "fullName")
    private String fullName;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "phone")
    private String phone;

    @XmlElement(name = "nationalId")
    private String nationalId;

    private LocalDate dateOfBirth;

    private List<Account> accounts = new ArrayList<>();

    public Long getCustomerId() { return id; }
    public void setCustomerId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "dateOfBirth")
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    @XmlElementWrapper(name = "accounts")
    @XmlElement(name = "account")
    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
}