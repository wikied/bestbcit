package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryItem {

    private Availability            availability;
    private ConditionEnum           condition;
    private String                  conditionDescription;
    private PackageWeightAndSize    packageWeightAndSize;
    private Product                 product;
    private String                  sku;
    private ArrayList<String>       groupIds[];

    //Constructor that accepts the end point SKU
    @RequestMapping(value = "{sku}")
    public InventoryItem inventoryItem(@PathVariable final int sku){
        InventoryItem item = new InventoryItem();

        return item;
    }

    //Getters

    public Availability getAvailability() {
        return availability;
    }

    public ConditionEnum getCondition() {
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

    public ArrayList<String>[] getGroupIds() {
        return groupIds;
    }


    //Setters
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    /**
     * Sets the available quantity of items to be shipped.
     *
     * @param availability int representing quantity.
     */
    public void setAvailability(int availability) {
        this.setAvailability(new Availability(availability));
    }

    public void setCondition(ConditionEnum condition) {
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

    public void setGroupIds(ArrayList<String>[] groupIds) {
        this.groupIds = groupIds;
    }
}
