package com.scriptofan.ecommerce.Ebay.InventoryItem;

import java.util.ArrayList;

public class Availability {

    private ArrayList<PickUpAtLocationAvailibility>     pickUpAtLocationAvailibilities;
    private ShipToLocationAvailibility                  shipToLocationAvailibility;

    public Availability(ShipToLocationAvailibility shipToLocationAvailibility){
        setShipToLocationAvailibility(shipToLocationAvailibility);
    }

    public ArrayList<PickUpAtLocationAvailibility> getPickUpAtLocationAvailibilities() {
        return pickUpAtLocationAvailibilities;
    }

    public void setPickUpAtLocationAvailibilities(ArrayList<PickUpAtLocationAvailibility> pickUpAtLocationAvailibilities) {
        this.pickUpAtLocationAvailibilities = pickUpAtLocationAvailibilities;
    }

    public ShipToLocationAvailibility getShipToLocationAvailibility() {
        return shipToLocationAvailibility;
    }

    public void setShipToLocationAvailibility(ShipToLocationAvailibility shipToLocationAvailibility) {
        this.shipToLocationAvailibility = shipToLocationAvailibility;
    }
}
