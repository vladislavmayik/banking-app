package com.solvd.bankingapp.models;

public class Currency {

    private Long   currencyId;
    private String currencyCode;
    private String name;
    private String symbol;

    public Currency() {}

    public Currency(Long currencyId, String currencyCode, String name, String symbol) {
        this.currencyId   = currencyId;
        this.currencyCode = currencyCode;
        this.name         = name;
        this.symbol       = symbol;
    }

    public Long getCurrencyId() { return currencyId; }
    public void   setCurrencyId(Long currencyId) { this.currencyId = currencyId; }
    public String getCurrencyCode() { return currencyCode; }
    public void   setCurrencyCode(String currencyCode) { this.currencyCode = currencyCode; }
    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getSymbol() { return symbol; }
    public void   setSymbol(String symbol) { this.symbol = symbol; }
}