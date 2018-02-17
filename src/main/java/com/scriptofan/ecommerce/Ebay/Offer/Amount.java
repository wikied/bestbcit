package com.scriptofan.ecommerce.Ebay.Offer;

// Used to express amount and applicable currency
public class Amount {

    // Represents the dollar value in the currency specified currency
    String value;

    // Three digit string representing the type of currency being used
    // All currency values listed are in the CurrencyCodeEnum
    // https://developer.ebay.com/api-docs/buy/feed/types/bas:CurrencyCodeEnum
    String currency;

    public Amount(String value, String currency) {
        this.value = value;
        this.currency = currency;
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
