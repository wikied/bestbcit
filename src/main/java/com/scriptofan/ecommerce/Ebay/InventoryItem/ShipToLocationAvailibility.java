package com.scriptofan.ecommerce.Ebay.InventoryItem;

/**
 * Shipping availability information for Ebay items.
 * Container that hodls the quantity of items available
 * to be shipped to a buyer's home address.
 *
 * @author  Daniel Zhang
 * @author  Patrick Charles-Lundaahl
 * @version 1.1
 */
public class ShipToLocationAvailibility {

    private int quantity;

    /**
     * Default constructor.
     */
    public ShipToLocationAvailibility() {
        this.quantity = 0;
    }

    /**
     * Constructor. Initializes quantity.
     *
     * @param quantity Desired quantity.
     */
    public ShipToLocationAvailibility(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
