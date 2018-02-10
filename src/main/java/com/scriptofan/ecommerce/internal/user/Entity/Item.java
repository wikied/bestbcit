package com.scriptofan.ecommerce.internal.user.Entity;

import java.net.URL;
import java.util.List;

public class Item {

    private String SKU;
    private int quantity;
    private double price;

    private String title;
    private String description;
    private String whoMade;

    private Condition condition;
    private List<URL> Images;

    /**
     * Primary constructor
     *
     * @param SKU
     * @param quantity
     * @param price
     * @param title
     */
    public Item(String SKU, int quantity, double price, String title) {
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

    public String getWhoMade() {
        return whoMade;
    }

    public void setWhoMade(String whoMade) {
        this.whoMade = whoMade;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<URL> getImages() {
        return Images;
    }

    public void setImages(List<URL> images) {
        Images = images;
    }

    /**
     * Represents an item's condition.
     */
    public enum Condition {
        // TODO: Fill
    }
}
