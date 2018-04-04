package com.scriptofan.ecommerce.Platforms.Etsy.Shipping;

public class ShippingInfo
{
    private int shipping_info_id;
    private int origin_country_id;
    private int destination_country_id;
    private String currency_code;
    private float primary_coat;
    private float secondary_coat;

    private int listing_id;

    private int region_id;
    private String origin_country_name;
    private String destination_country_name;
    public int getShipping_info_id() {
        return shipping_info_id;
    }

    public void setShipping_info_id(int shipping_info_id) {
        this.shipping_info_id = shipping_info_id;
    }

    public int getOrigin_country_id() {
        return origin_country_id;
    }

    public void setOrigin_country_id(int origin_country_id) {
        this.origin_country_id = origin_country_id;
    }

    public int getDestination_country_id() {
        return destination_country_id;
    }

    public void setDestination_country_id(int destination_country_id) {
        this.destination_country_id = destination_country_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public float getPrimary_coat() {
        return primary_coat;
    }

    public void setPrimary_coat(float primary_coat) {
        this.primary_coat = primary_coat;
    }

    public float getSecondary_coat() {
        return secondary_coat;
    }

    public void setSecondary_coat(float secondary_coat) {
        this.secondary_coat = secondary_coat;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getOrigin_country_name() {
        return origin_country_name;
    }

    public void setOrigin_country_name(String origin_country_name) {
        this.origin_country_name = origin_country_name;
    }

    public String getDestination_country_name() {
        return destination_country_name;
    }

    public void setDestination_country_name(String destination_country_name) {
        this.destination_country_name = destination_country_name;
    }
}
