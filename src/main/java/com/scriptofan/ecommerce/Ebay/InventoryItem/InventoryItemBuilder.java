package com.scriptofan.ecommerce.Ebay.InventoryItem;

import com.sun.tools.internal.xjc.outline.Aspect;

import java.util.ArrayList;

public class InventoryItemBuilder {


    public Availability availabilityObjectCreator(int quantity){
        ShipToLocationAvailibility shipToLocationAvailibility = new ShipToLocationAvailibility(quantity);
        Availability availability = new Availability(shipToLocationAvailibility);
        return availability;
    }

    public Product productObjectCreator(){
        Product product = new Product();
        ArrayList<String> images = new ArrayList<>();
        images.add("http://i.ebayimg.com/images/i/182196556219-0-1/s-l1000.jpg");
        product.setTitle("GoPro Hero4 Helmet Cam");
        product.setDescription("New GoPro Hero4 Helmet Cam. Unopened box");
        product.setBrand("GoPro");
        product.setMpn("CHDHX-401");
        product.setImageUrls(images);
        product.setAspects();
        return product;
    }

    public InventoryItem itemObjectCreator(){
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setCondition(ConditionEnum.NEW);
        return inventoryItem;
    }

}
