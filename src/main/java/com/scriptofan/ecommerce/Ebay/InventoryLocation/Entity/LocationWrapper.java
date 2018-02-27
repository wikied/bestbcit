package com.scriptofan.ecommerce.Ebay.InventoryLocation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationWrapper {

    private String      name;
    private InventoryLocation location;
    private String      merchantLocationStatus;
    private String[]    locationTypes;
    private String      merchantLocationKey;

    public LocationWrapper() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryLocation getLocation() {
        return location;
    }

    public void setLocation(InventoryLocation location) {
        this.location = location;
    }

    public String getMerchantLocationStatus() {
        return merchantLocationStatus;
    }

    public void setMerchantLocationStatus(String merchantLocationStatus) {
        this.merchantLocationStatus = merchantLocationStatus;
    }

    public String[] getLocationTypes() {
        return locationTypes;
    }

    public void setLocationTypes(String[] locationTypes) {
        this.locationTypes = locationTypes;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }
}
