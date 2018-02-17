package com.scriptofan.ecommerce.Ebay.Offer;

// Specifies the listing price for the product
public class PricingSummary {

    // Listing price of the offer
    Amount price;

    // Constructor
    public PricingSummary(Amount price) {
        this.price = price;
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }
}
