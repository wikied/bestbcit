package com.scriptofan.ecommerce.internal.user.Entity;

import java.net.URL;
import java.util.List;

public class LocalItem {

    private String          SKU;
    private int             quantity;
    private Condition       condition;
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

    /**
     * Primary constructor
     *
     * @param SKU
     * @param quantity
     * @param price
     * @param title
     */
    public LocalItem(String SKU, int quantity, double price, String title) {
        this.SKU = SKU;
        this.quantity = quantity;
        this.price = price;
        this.title = title;
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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
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

    /**
     * Represents an item's condition.
     */
    public enum Condition {
        // TODO: Fill
    }
}
