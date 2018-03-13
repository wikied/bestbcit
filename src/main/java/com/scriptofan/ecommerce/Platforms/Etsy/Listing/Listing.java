package com.scriptofan.ecommerce.Platforms.Etsy.Listing;

public class Listing {
    private int quantity;
    private int title;
    private int description;
    private float price;
    private boolean is_supply;
    private who_made who_made;
    private String when_made;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isIs_supply() {
        return is_supply;
    }

    public void setIs_supply(boolean is_supply) {
        this.is_supply = is_supply;
    }

    public Listing.who_made getWho_made() {
        return who_made;
    }

    public void setWho_made(Listing.who_made who_made) {
        this.who_made = who_made;
    }

    public String getWhen_made() {
        return when_made;
    }

    public void setWhen_made(String when_made) {
        this.when_made = when_made;
    }

    enum who_made{
        i_did,
        collective,
        someone_else
    }

}


