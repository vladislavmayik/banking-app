package com.solvd.bankingapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "street")
    private String street;

    @XmlElement(name = "city")
    private String city;

    @XmlElement(name = "country")
    private String country;

    @XmlElement(name = "postalCode")
    private String postalCode;

    @XmlElement(name = "customerId")
    private Long customerId;

    public Address() {}

    public Address(Long id, String street, String city,
                   String country, String postalCode, Long customerId) {
        this.id  = id;
        this.street     = street;
        this.city       = city;
        this.country    = country;
        this.postalCode = postalCode;
        this.customerId = customerId;
    }

    public Long   getAddressId()                  { return id; }
    public void   setAddressId(Long id)   { this.id = id; }
    public String getStreet()                     { return street; }
    public void   setStreet(String street)         { this.street = street; }
    public String getCity()                       { return city; }
    public void   setCity(String city)             { this.city = city; }
    public String getCountry()                     { return country; }
    public void   setCountry(String country)       { this.country = country; }
    public String getPostalCode()                  { return postalCode; }
    public void   setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public Long   getCustomerId()                  { return customerId; }
    public void   setCustomerId(Long customerId)   { this.customerId = customerId; }
}