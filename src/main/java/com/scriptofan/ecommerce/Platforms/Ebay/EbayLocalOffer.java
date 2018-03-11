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

public class EbayLocalOffer extends Offer{

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String CREATE_OR_REPLACE_INVENTORY_ITEM_URI
                                = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/";

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {
        createOrReplaceInventoryItem(getLocalItem().getUser().getUserToken(), getLocalItem().getField("sku"));

    }


    private String createOrReplaceInventoryItem(String token, String sku) {
        InventoryItem                 inventoryItem;
        HttpHeaders                   headers;
        HttpEntity<InventoryItem>     httpEntity;
        RestTemplate                  template;

        headers = new HttpHeaders();
        headers.set("authorization", TOKEN_PREFIX + getLocalItem().getUser().getUserToken());
        headers.set("content-language", CONTENT_LANGUAGE);

        template = new RestTemplate();

        inventoryItem = EbayCreateOrReplaceItemService.createInventoryItem(this);

        httpEntity = new HttpEntity<>(inventoryItem, headers);

        //inventoryItem = template.exchange(CREATE_OR_REPLACE_INVENTORY_ITEM_URI + sku,)




        return null;
    }

}
