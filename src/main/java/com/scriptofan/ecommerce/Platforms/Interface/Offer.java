package com.scriptofan.ecommerce.Platforms.Interface;

public abstract class Offer {

    private int quantity;

    public Offer() {
        quantity = 0;
    }



    /*
     * Returns an instance of the PlatformPublishingService associated with
     * this type of offer (i.e. "EbayPublishingService, EtsyPublishingService,"
     * etc.)
     */
    public abstract PlatformPublishingService getPlatformPublishingService();



    /**
     * Returns this offer's quantity.
     * @return this offer's quantity.
     */
    public final int getQuantity() {
        return quantity;
    }



    /**
     * Sets quantity associated with this offer.
     * @param quantity
     * @throws IllegalArgumentException Quantity must be non-negative.
     */
    public final void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
        else {
            throw new IllegalArgumentException("Quantity may not be less than 0");
        }
    }
}
