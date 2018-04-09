package com.scriptofan.ecommerce.Platforms.Etsy.Listing;

import java.util.List;

public class ListingInventory {
    private List<ListingProduct> products;
    private List<Integer> price_on_property;
    private List<Integer> quantity_on_property;
    private List<String> sku_on_property;

    public List<ListingProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ListingProduct> products) {
        this.products = products;
    }

    public List<Integer> getPrice_on_property() {
        return price_on_property;
    }

    public void setPrice_on_property(List<Integer> price_on_property) {
        this.price_on_property = price_on_property;
    }

    public List<Integer> getQuantity_on_property() {
        return quantity_on_property;
    }

    public void setQuantity_on_property(List<Integer> quantity_on_property) {
        this.quantity_on_property = quantity_on_property;
    }

    public List<String> getSku_on_property() {
        return sku_on_property;
    }

    public void setSku_on_property(List<String> sku_on_property) {
        this.sku_on_property = sku_on_property;
    }
}
