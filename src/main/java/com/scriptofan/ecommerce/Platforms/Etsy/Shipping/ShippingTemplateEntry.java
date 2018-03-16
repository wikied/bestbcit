package com.scriptofan.ecommerce.Platforms.Etsy.Shipping;

public class ShippingTemplateEntry {
    private int shipping_template_entry_id;
    private int shipping_template_id;
    private String currency_code;
    private int origin_country_id;
    private int destination_country_id;
    private int destination_region_id;
    private float primary_cost;
    private float secondary_cost;

    public int getShipping_template_entry_id() {
        return shipping_template_entry_id;
    }

    public void setShipping_template_entry_id(int shipping_template_entry_id) {
        this.shipping_template_entry_id = shipping_template_entry_id;
    }

    public int getShipping_template_id() {
        return shipping_template_id;
    }

    public void setShipping_template_id(int shipping_template_id) {
        this.shipping_template_id = shipping_template_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
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

    public int getDestination_region_id() {
        return destination_region_id;
    }

    public void setDestination_region_id(int destination_region_id) {
        this.destination_region_id = destination_region_id;
    }

    public float getPrimary_cost() {
        return primary_cost;
    }

    public void setPrimary_cost(float primary_cost) {
        this.primary_cost = primary_cost;
    }

    public float getSecondary_cost() {
        return secondary_cost;
    }

    public void setSecondary_cost(float secondary_cost) {
        this.secondary_cost = secondary_cost;
    }
}
