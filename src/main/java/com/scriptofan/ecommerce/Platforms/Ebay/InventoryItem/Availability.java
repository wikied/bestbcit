package com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem;

import java.util.ArrayList;

public class Availability {

    private ArrayList<PickUpAtLocationAvailibility>     pickUpAtLocationAvailibilities;
    private ShipToLocationAvailability shipToLocationAvailibility;

    public ArrayList<PickUpAtLocationAvailibility> getPickUpAtLocationAvailibilities() {
        return pickUpAtLocationAvailibilities;
    }

    public void setPickUpAtLocationAvailibilities(ArrayList<PickUpAtLocationAvailibility> pickUpAtLocationAvailibilities) {
        this.pickUpAtLocationAvailibilities = pickUpAtLocationAvailibilities;
    }

    public ShipToLocationAvailability getShipToLocationAvailibility() {
        return shipToLocationAvailibility;
    }

    public void setShipToLocationAvailibility(ShipToLocationAvailability shipToLocationAvailibility) {
        this.shipToLocationAvailibility = shipToLocationAvailibility;
    }
}
