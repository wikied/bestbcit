package com.scriptofan.ecommerce.Platforms.Ebay.Offer;

// Specifies the listing price for the product
public class PricingSummary {

    // Listing price of the offer
    Amount price;

    // Constructor
    public PricingSummary() {
    }

    public Amount getPrice() {
        return price;
    }

    public void setPrice(Amount price) {
        this.price = price;
    }
}
