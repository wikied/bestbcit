package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EbayOffer extends Offer{

    public void post() {
        //createOrReplaceInventoryItem()

    }


    private String createOrReplaceInventoryItem(String token, String url) {
        InventoryItem          body;
        HttpHeaders            header;
        HttpEntity<String>     request;
        ResponseEntity<String> response;
        RestTemplate           template;



        return null;
    }

}
