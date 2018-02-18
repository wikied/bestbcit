package com.scriptofan.ecommerce.internal.user.Entity;

import sun.net.www.content.text.Generic;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalItem {

    private String          SKU;
    private int             quantity;
    private String          condition;
    private String          title;
    private String          description;
    private List<String>    images;

    private double          height;
    private double          length;
    private double          width;
    private String          dimensionUnit;

    private double          weight;
    private String          weightUnit;

    private double          price;

    private Map<String, GenericOffer> offers;

    /**
     * Default constructor.
     */
    public LocalItem() {
        this.offers = new HashMap<String, GenericOffer>();
    }

    // Accessors & Mutators
    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        images = images;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public void setDimensions(double length, double width, double height, String unit) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
        this.setDimensionUnit(unit);
    }

    public void setWeight(double weight, String unit) {
        this.setWeight(weight);
        this.setWeightUnit(unit);
    }

    /**
     * Adds an offer to this item.
     *
     * @param offer Offer to add.
     */
    public void addOffer(GenericOffer offer) {
        this.offers.put(offer.getPlatformAccountId(), offer);
    }

    /**
     * Retrieves an offer for this item, specified by the linked platformAccountId.
     *
     * @param platformAccountId platformAccountId that is associated with the offer.
     * @return The offer associated with this item and the specified platformAccountId.
     */
    public GenericOffer getOffer(String platformAccountId) {
        return this.offers.get(platformAccountId);
    }
}
