package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

/**
 * Container for the availability information associated with Ebay
 * items.
 *
 * @author  Jason Huang
 * @author  Patrick Charles-Lundaahl
 * @version 1.1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Availability {

    private ArrayList<PickUpAtLocationAvailibility>     pickUpAtLocationAvailibilities;
    private ShipToLocationAvailibility                  shipToLocationAvailibility;

    /**
     * Default constructor. Initializes quantity to 0 and pickup availability
     * to null.
     */
    public Availability() {
        this.pickUpAtLocationAvailibilities = null;
        this.shipToLocationAvailibility     = new ShipToLocationAvailibility();
    }

    /**
     * Constructor. Initializes quantity.
     *
     * @param quantity Number of shippable items.
     */
    public Availability(int quantity) {
        this.pickUpAtLocationAvailibilities = null;
        this.shipToLocationAvailibility     = new ShipToLocationAvailibility(quantity);
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
