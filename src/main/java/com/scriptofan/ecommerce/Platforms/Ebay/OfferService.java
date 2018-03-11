package com.scriptofan.ecommerce.Platforms.Ebay;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Amount;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;

import com.scriptofan.ecommerce.Platforms.Ebay.Offer.OfferResponse;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.PricingSummary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OfferService {

    private static final String POST_OFFERS_URL     = "https://api.sandbox.ebay.com/sell/inventory/v1/offer";
    private static final String GET_OFFERS_URL      = POST_OFFERS_URL + "?";
    private static final String CONTENT_LANGUAGE = "en-US";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public OfferService (){

    }

    public Offer offerBuilder(EbayLocalOffer ebayoffer){
        Offer           offer = new Offer();
        Amount  amount = new Amount();
        PricingSummary pricingSummary = new PricingSummary();
        amount.setValue(ebayoffer.getLocalItem().getField("value"));
        amount.setCurrency(ebayoffer.getLocalItem().getField("currencyCode"));
        pricingSummary.setPrice(amount);
        offer.setPricingSummary(pricingSummary);
        offer.setSku(ebayoffer.getLocalItem().getField("sku"));
        offer.setMerchantLocationKey(ebayoffer.getLocalItem().getField("merchantLocationKey"));
        offer.setCategoryId(ebayoffer.getLocalItem().getField("categoryId"));
        offer.setFormat(ebayoffer.getLocalItem().getField("format"));
        offer.setMarketplaceId(ebayoffer.getLocalItem().getField("marketplaceId"));
        //offer.setListingPolicies();
        return offer;
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

    public String createOffer(Offer offer, String token){

        OfferResponse     offerResponse;
        String                 response;
        RestTemplate       restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<Offer>    httpEntity;

        httpHeaders         = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content Language", CONTENT_LANGUAGE);
        httpEntity = new HttpEntity<>(offer, httpHeaders);

        restTemplate        = new RestTemplate();
        restTemplate.setErrorHandler(new LocationService.MyErrorHandler());

        try {
            offerResponse = restTemplate.exchange(POST_OFFERS_URL, HttpMethod.POST, httpEntity, OfferResponse.class).getBody();
            response = offerResponse.getOfferId();
            return response;
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = "Could not create offer\n" + ex.getMessage();
        }
        return response;

    }


}
