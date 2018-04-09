package com.scriptofan.ecommerce.Platforms.Ebay.Entity.InventoryItem;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class InventoryItem {

    private Availability availability;
    private String condition;
    private String conditionDescription;
    private PackageWeightAndSize packageWeightAndSize;
    private Product product;
    private String sku;
    private ArrayList<String> groupids[];

    //Constructor that accepts the end point SKU
    // @RequestMapping(value = "{sku}")
    public InventoryItem inventoryItem(final int sku){
        InventoryItem item = new InventoryItem();

        return item;
    }

    //Getters

    public Availability getAvailability() {
        return availability;
    }

    public String getCondition() {
        return condition;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public PackageWeightAndSize getPackageWeightAndSize() {
        return packageWeightAndSize;
    }

    public Product getProduct() {
        return product;
    }

    public String getSku() {
        return sku;
    }

    public ArrayList<String>[] getGroupids() {
        return groupids;
    }


    //Setters
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public void setPackageWeightAndSize(PackageWeightAndSize packageWeightAndSize) {
        this.packageWeightAndSize = packageWeightAndSize;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setGroupids(ArrayList<String>[] groupids) {
        this.groupids = groupids;
    }
}
