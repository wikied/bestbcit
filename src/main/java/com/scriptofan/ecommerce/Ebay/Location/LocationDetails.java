package com.scriptofan.ecommerce.Ebay.Location;

public class LocationDetails {
    private Address address;
    private GeoCoordinates geoCoordinates;

    public LocationDetails(Address address){
        setAddress(address);
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public GeoCoordinates getGeoCoordinates() {
        return geoCoordinates;
    }

    public void setGeoCoordinates(GeoCoordinates geoCoordinates) {
        this.geoCoordinates = geoCoordinates;
    }
}
