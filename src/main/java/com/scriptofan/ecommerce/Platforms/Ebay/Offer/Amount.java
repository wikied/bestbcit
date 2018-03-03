package com.scriptofan.ecommerce.Platforms.Ebay.Offer;

// Used to express amount and applicable currency
public class Amount {

    // Represents the dollar value in the currency specified currency
    String value;

    // Three digit string representing the type of currency being used
    // All currency values listed are in the CurrencyCodeEnum
    // https://developer.ebay.com/api-docs/buy/feed/types/bas:CurrencyCodeEnum
    String currency;

    public Amount() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}