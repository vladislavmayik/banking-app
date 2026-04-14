package com.solvd.bankingapp.models;

public class Address {

    private Long   addressId;
    private String street;
    private String city;
    private String country;
    private String postalCode;
    private Long   customerId;

    public Address() {}

    public Address(Long addressId, String street, String city,
                   String country, String postalCode, Long customerId) {
        this.addressId  = addressId;
        this.street     = street;
        this.city       = city;
        this.country    = country;
        this.postalCode = postalCode;
        this.customerId = customerId;
    }

    public Long   getAddressId()                  { return addressId; }
    public void   setAddressId(Long addressId)   { this.addressId = addressId; }
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