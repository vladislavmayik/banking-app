package com.solvd.bankingapp.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "currencyCode")
    private String currencyCode;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "symbol")
    private String symbol;

    public Currency() {}

    public Currency(Long id, String currencyCode, String name, String symbol) {
        this.id   = id;
        this.currencyCode = currencyCode;
        this.name         = name;
        this.symbol       = symbol;
    }

    public Long getCurrencyId() { return id; }
    public void   setCurrencyId(Long id) { this.id = id; }
    public String getCurrencyCode() { return currencyCode; }
    public void   setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getSymbol() { return symbol; }
    public void   setSymbol(String symbol) { this.symbol = symbol; }
}