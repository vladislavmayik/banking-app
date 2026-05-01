package com.solvd.bankingapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    @XmlElement(name = "customerId")
    @JsonProperty("customerId")
    private Long id;

    @XmlElement(name = "fullName")
    @JsonProperty("fullName")
    private String fullName;

    @XmlElement(name = "email")
    @JsonProperty("email")
    private String email;

    @XmlElement(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @XmlElement(name = "nationalId")
    @JsonProperty("nationalId")
    private String nationalId;

    @JsonProperty("dateOfBirth")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @JsonProperty("accounts")
    private List<Account> accounts = new ArrayList<>();

//    private Customer() {}

    public static class Builder {
        private Long id;
        private String fullName;
        private String email;
        private String phone;
        private String nationalId;
        private LocalDate dateOfBirth;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder nationalId(String nationalId) {
            this.nationalId = nationalId;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.id = this.id;
            customer.fullName = this.fullName;
            customer.email = this.email;
            customer.phone = this.phone;
            customer.nationalId = this.nationalId;
            customer.dateOfBirth = this.dateOfBirth;
            return customer;
        }
    }

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