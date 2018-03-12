package com.scriptofan.ecommerce.Platforms.Ebay.Offer;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayListing;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class EbayPublishOffer {

    private static final String PUBLISH_OFFER_URI = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/publish/";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_LANGUAGE = "en-US";

    public static String publishEbayOffer(String offerId, String token) {
        RestTemplate restTemplate;
        HttpHeaders httpHeaders;
        HttpEntity<String> httpEntity;
        String response;
        EbayListing ebayListing = null;

        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);
        httpHeaders.set("Accept","application/json");
        httpHeaders.set("Content-Type", "application/json");

        httpEntity = new HttpEntity<>(offerId, httpHeaders);

        try {
           ebayListing =  restTemplate.exchange(PUBLISH_OFFER_URI + offerId,
                                   HttpMethod.POST, httpEntity, EbayListing.class).getBody();
            System.err.println(ebayListing);

        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }

        return ebayListing.toString();

    }
}
