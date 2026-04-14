package com.solvd.bankingapp.models;

import java.time.LocalDate;

public class IdentityDocument {

    private Long      documentId;
    private String    documentNumber;
    private String    issuerCountry;
    private LocalDate expiryDate;
    private String    type;
    private Long      customerId;

    public IdentityDocument() {}

    public IdentityDocument(Long documentId, String documentNumber, String issuerCountry,
                            LocalDate expiryDate, String type, Long customerId) {
        this.documentId     = documentId;
        this.documentNumber = documentNumber;
        this.issuerCountry  = issuerCountry;
        this.expiryDate     = expiryDate;
        this.type           = type;
        this.customerId     = customerId;
    }

    public Long      getDocumentId()                            { return documentId; }
    public void      setDocumentId(Long documentId)               { this.documentId = documentId; }
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