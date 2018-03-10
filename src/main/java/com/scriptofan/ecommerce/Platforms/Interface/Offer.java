package com.scriptofan.ecommerce.Platforms.Interface;

import com.scriptofan.ecommerce.LocalItem.LocalItem;

public abstract class Offer {

    private int         quantity;
    private LocalItem   localItem;

    public Offer(LocalItem localItem) {
        this.quantity   = 0;
        this.localItem  = localItem;
    }


    /*
     * Posts this offer to the respective platform.
     */
    public abstract void post();



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



    /*
     * Returns the associated localItem.
     */
    public LocalItem getLocalItem() {
        return localItem;
    }
}
