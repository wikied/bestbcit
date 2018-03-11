package com.scriptofan.ecommerce.Platforms.Ebay;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies.Entity.FulfillmentPolicy;
import com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies.FulfillmentPolicyService;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.InventoryItem;
import com.scriptofan.ecommerce.Platforms.Ebay.Location.Location;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.ListingPolicies;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;

import com.sun.tools.classfile.ConstantPool;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OfferService {

    private static final String POST_OFFERS_URL   = "https://api.sandbox.ebay.com/sell/inventory/v1/offer";
    private static final String GET_OFFERS_URL = POST_OFFERS_URL + "?";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public OfferService (){

    }



    public Offer[] getOffers(String token){
        Offer[]             itemOffers;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<String>  httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

        itemOffers = restTemplate
                .exchange(
                        GET_OFFERS_URL,
                        HttpMethod.GET,
                        httpEntity,
                        Offer[].class)
                .getBody();

        return itemOffers;

    }

    public Offer offerBuilder(EbayOffer ebayoffer){
        Offer           offer = new Offer();
        offer.setSku(ebayoffer.getLocalItem().getField("sku"));
        offer.setMerchantLocationKey(ebayoffer.getLocalItem().getField("merchantLocationKey"));
        offer.setCategoryId(ebayoffer.getLocalItem().getField("categoryId"));
        offer.setFormat(ebayoffer.getLocalItem().getField("format"));
        offer.setMarketplaceId(ebayoffer.getLocalItem().getField("markerplaceId"));
        //Missing listing policies
        return offer;
    }

    public String createOffer(Offer offer, String token){

        String              completeUrl;
        String              response;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<Offer>   httpEntity;

        completeUrl         = POST_OFFERS_URL;

        httpHeaders         = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpEntity = new HttpEntity<>(offer, httpHeaders);

        restTemplate        = new RestTemplate();
        restTemplate.setErrorHandler(new LocationService.MyErrorHandler());

        try {
            restTemplate.exchange(completeUrl, HttpMethod.POST, httpEntity, Offer.class);
            response = "success";
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }

        return response;
    }


}
