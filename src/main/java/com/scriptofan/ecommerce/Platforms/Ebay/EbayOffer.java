package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Availability;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Product;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.ShipToLocationAvailibility;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class EbayOffer extends Offer{

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String CREATE_OR_REPLACE_INVENTORY_ITEM_URI
                                = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";

    public EbayOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {
        createOrReplaceInventoryItem(getLocalItem().getUser().getUserToken(), getLocalItem().getField("sku"));

    }


    private String createOrReplaceInventoryItem(String token, String sku) {
        InventoryItem          inventoryItem;
        HttpHeaders            headers;
        HttpEntity<String>     request;
        ResponseEntity<String> response;
        RestTemplate           template;

        headers = new HttpHeaders();
        headers.set("authorization", TOKEN_PREFIX + getLocalItem().getUser().getUserToken());
        headers.set("content-language", CONTENT_LANGUAGE);

        inventoryItem = new InventoryItem();

        // Sets the Availability for the Inventory Item
        Availability availability = new Availability();
        ShipToLocationAvailibility shipToLocationAvailibility = new ShipToLocationAvailibility();
        shipToLocationAvailibility.setQuantity(getQuantity());
        availability.setShipToLocationAvailibility(shipToLocationAvailibility);
        inventoryItem.setAvailability(availability);

        // Sets the condition
        inventoryItem.setCondition(getLocalItem().getField("condition"));

        // Sets the product title and description
        Product product = new Product();
        product.setTitle(getLocalItem().getField("title"));
        product.setDescription(getLocalItem().getField("productDescription"));

        // Sets the imageUrls
        String listOfImageUrls = getLocalItem().getField("imageUrls");
        String[] imageUrls = listOfImageUrls.split("\\s+");
        ArrayList<String> productImageUrls = new ArrayList<>();

        for(String imageUrl : imageUrls) {
            productImageUrls.add(imageUrl);
        }

        product.setImageUrls(productImageUrls);

        return null;
    }

}
