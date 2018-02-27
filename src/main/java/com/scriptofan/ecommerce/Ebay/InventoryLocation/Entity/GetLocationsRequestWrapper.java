package com.scriptofan.ecommerce.Ebay.InventoryLocation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLocationsRequestWrapper {

    private LocationWrapper[] locations;

    public GetLocationsRequestWrapper() { }

    public LocationWrapper[] getLocations() {
        return locations;
    }

    public void setLocations(LocationWrapper[] locations) {
        this.locations = locations;
    }
}
