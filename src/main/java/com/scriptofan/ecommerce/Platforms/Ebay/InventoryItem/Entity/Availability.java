package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity;

import java.util.ArrayList;

public class Availability {

    private ArrayList<PickUpAtLocationAvailibility> pickUpAtLocationAvailabilities;
    private ShipToLocationAvailability shipToLocationAvailability;

    public ArrayList<PickUpAtLocationAvailibility> getPickUpAtLocationAvailabilities() {
        return pickUpAtLocationAvailabilities;
    }

    public void setPickUpAtLocationAvailabilities(ArrayList<PickUpAtLocationAvailibility> pickUpAtLocationAvailabilities) {
        this.pickUpAtLocationAvailabilities = pickUpAtLocationAvailabilities;
    }

    public ShipToLocationAvailability getShipToLocationAvailability() {
        return shipToLocationAvailability;
    }

    public void setShipToLocationAvailability(ShipToLocationAvailability shipToLocationAvailability) {
        this.shipToLocationAvailability = shipToLocationAvailability;
    }
}
