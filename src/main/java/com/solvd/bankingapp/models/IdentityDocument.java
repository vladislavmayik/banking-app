package com.solvd.bankingapp.models;

import com.solvd.bankingapp.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "identityDocument")
@XmlAccessorType(XmlAccessType.FIELD)
public class IdentityDocument {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "documentNumber")
    private String documentNumber;

    @XmlElement(name = "issuerCountry")
    private String issuerCountry;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "expiryDate")
    private LocalDate expiryDate;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "customerId")
    private Long customerId;

    public IdentityDocument() {}

    public IdentityDocument(Long id, String documentNumber, String issuerCountry,
                            LocalDate expiryDate, String type, Long customerId) {
        this.id     = id;
        this.documentNumber = documentNumber;
        this.issuerCountry  = issuerCountry;
        this.expiryDate     = expiryDate;
        this.type           = type;
        this.customerId     = customerId;
    }

    public Long      getDocumentId()                            { return id; }
    public void      setDocumentId(Long id)               { this.id = id; }
    public String    getDocumentNumber()                       { return documentNumber; }
    public void      setDocumentNumber(String documentNumber)     { this.documentNumber = documentNumber; }
    public String    getIssuerCountry()                         { return issuerCountry; }
    public void      setIssuerCountry(String issuerCountry)       { this.issuerCountry = issuerCountry; }
    public LocalDate getExpiryDate()                            { return expiryDate; }
    public void      setExpiryDate(LocalDate expiryDate)          { this.expiryDate = expiryDate; }
    public String    getType()                                  { return type; }
    public void      setType(String type)                         { this.type = type; }
    public Long      getCustomerId()                            { return customerId; }
    public void      setCustomerId(Long customerId)               { this.customerId = customerId; }
}