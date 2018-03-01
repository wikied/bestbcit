package com.scriptofan.ecommerce.Ebay.InventoryItem;

public class ShipToLocationAvailibility {

    private int quantity;

    public ShipToLocationAvailibility(int quantity){
        setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
