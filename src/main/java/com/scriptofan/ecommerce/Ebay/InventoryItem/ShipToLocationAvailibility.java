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

    /**
     * Represents the default item quantity if none is specified.
     */
    public static final int DEFAULT_QUANTITY = 0;

    private int quantity;

    /**
     * Default constructor.
     */
    public ShipToLocationAvailibility() {
        this.quantity = DEFAULT_QUANTITY;
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
