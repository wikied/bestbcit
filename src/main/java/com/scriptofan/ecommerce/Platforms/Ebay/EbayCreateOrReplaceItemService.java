package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Availability;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Product;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.ShipToLocationAvailability;

import java.util.ArrayList;

public class EbayCreateOrReplaceItemService {

    public static InventoryItem createInventoryItem(EbayLocalOffer offer) {
        InventoryItem inventoryItem = new InventoryItem();

        inventoryItem.setAvailability(createAvailability(offer));
        inventoryItem.setCondition(offer.getLocalItem().getField("condition"));
        inventoryItem.setProduct(createProduct(offer));

        return inventoryItem;
    }

    // Creates a Availability object for the inventory item
    private static Availability createAvailability(EbayLocalOffer offer) {
        Availability availability = new Availability();
        ShipToLocationAvailability shipToLocationAvailability= new ShipToLocationAvailability();
        shipToLocationAvailability.setQuantity(offer.getQuantity());
        availability.setShipToLocationAvailibility(shipToLocationAvailability);
        return availability;
    }

    // Creates a Product object for the inventory item
    private static Product createProduct(EbayLocalOffer offer) {
        Product product = new Product();

        product.setTitle(offer.getLocalItem().getField("title"));
        product.setDescription(offer.getLocalItem().getField("productDescription"));

        // Sets the imageUrls
        String listOfImageUrls = offer.getLocalItem().getField("imageUrls");
        String[] imageUrls = listOfImageUrls.split("\\s+");
        ArrayList<String> productImageUrls = new ArrayList<>();

        for(String imageUrl : imageUrls) {
            productImageUrls.add(imageUrl);
        }
        product.setImageUrls(productImageUrls);

        return product;
    }


}
