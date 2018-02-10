package com.scriptofan.ecommerce.Ebay.Offer;

// Type is sued to express amount and applicable currency
public class Amount {

    // Represents the dollar value in the currency specified currency
    String value;

    // Three digit string representing the type of currency being used
    // All currency values listed are in the CurrencyCodeEnum
    // https://developer.ebay.com/api-docs/buy/feed/types/bas:CurrencyCodeEnum
    String currency;
}
