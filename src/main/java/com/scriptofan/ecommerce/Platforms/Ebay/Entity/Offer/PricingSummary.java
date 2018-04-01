package com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer;

// Specifies the listing price for the product
public class PricingSummary {

    // Listing price of the offer
    private Amount price;

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
