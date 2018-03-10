package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class EbayOffer extends Offer{

    public void post() {

    }


    private String createOrReplaceInventoryItem(String token, String url) {
        String             body;
        HttpHeaders        header;
        HttpEntity<String> request;
        ResponseEntity<String> response;
        return null;
    }

}
