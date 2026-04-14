package com.solvd.bankingapp.models;

import java.time.LocalDate;

public class Customer {

    private Long   customerId;
    private String fullName;
    private String email;
    private String phone;
    private String nationalId;
    private LocalDate dateOfBirth;

    public Customer() {}

    public Customer(Long customerId, String fullName, String email, String phone,
                    String nationalId, LocalDate dateOfBirth) {
        this.customerId  = customerId;
        this.fullName    = fullName;
        this.email       = email;
        this.phone       = phone;
        this.nationalId  = nationalId;
        this.dateOfBirth = dateOfBirth;
    }

    public Long     getCustomerId() { return customerId; }
    public void     setCustomerId(Long customerId) { this.customerId = customerId; }
    public String    getFullName()    { return fullName; }
    public void      setFullName(String fullName) { this.fullName = fullName; }
    public String    getEmail()       { return email; }
    public void      setEmail(String email) { this.email = email; }
    public String    getPhone()       { return phone; }
    public void      setPhone(String phone) { this.phone = phone; }
    public String    getNationalId()  { return nationalId; }
    public void      setNationalId(String nationalId) { this.nationalId = nationalId; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void      setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth;}
}