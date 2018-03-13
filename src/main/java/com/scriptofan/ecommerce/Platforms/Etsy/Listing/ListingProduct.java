package com.scriptofan.ecommerce.Platforms.Etsy.Listing;

import groovy.lang.PropertyValue;

import java.lang.reflect.Array;
import java.util.List;

public class ListingProduct {
    private int product_id;
    private List<PropertyValue> propertyValues;
    private String sku;
    private List<ListingOffering> offerings;
    private boolean is_deleted;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<ListingOffering> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<ListingOffering> offerings) {
        this.offerings = offerings;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
