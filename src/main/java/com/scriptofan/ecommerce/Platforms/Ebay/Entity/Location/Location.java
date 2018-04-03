package com.scriptofan.ecommerce.Platforms.Ebay.Entity.Location;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private LocationDetails location;
    private String locationAdditionalInformation;
    private String locationInstructions;
    private StoreTypeEnum locationTypes[];
    private String locationWebUrl;
    private StatusEnum merchantLocationStatus;
    private String name;
    private OperatingHours operatingHours[];
    private String phone;
    private SpecialHours specialHours[];

    public Location(LocationDetails locationDetails){
        setLocation(locationDetails);
    }

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    public String getLocationAdditionalInformation() {
        return locationAdditionalInformation;
    }

    public void setLocationAdditionalInformation(String locationAdditionalInformation) {
        this.locationAdditionalInformation = locationAdditionalInformation;
    }

    public String getLocationInstructions() {
        return locationInstructions;
    }

    public void setLocationInstructions(String locationInstructions) {
        this.locationInstructions = locationInstructions;
    }

    public StoreTypeEnum[] getLocationTypes() {
        return locationTypes;
    }

    public void setLocationTypes(StoreTypeEnum[] locationTypes) {
        this.locationTypes = locationTypes;
    }

    public String getLocationWebUrl() {
        return locationWebUrl;
    }

    public void setLocationWebUrl(String locationWebUrl) {
        this.locationWebUrl = locationWebUrl;
    }

    public StatusEnum getMerchantLocationStatus() {
        return merchantLocationStatus;
    }

    public void setMerchantLocationStatus(StatusEnum merchantLocationStatus) {
        this.merchantLocationStatus = merchantLocationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperatingHours[] getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(OperatingHours[] operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SpecialHours[] getSpecialHours() {
        return specialHours;
    }

    public void setSpecialHours(SpecialHours[] specialHours) {
        this.specialHours = specialHours;
    }
}
