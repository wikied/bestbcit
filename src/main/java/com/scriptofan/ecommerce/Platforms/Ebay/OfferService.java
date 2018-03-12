package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.Offer.*;
import com.scriptofan.ecommerce.User.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.IOException;

@Service
public class OfferService {

    private static final String POST_OFFERS_URL     = "https://api.sandbox.ebay.com/sell/inventory/v1/offer";
    private static final String GET_OFFERS_URL      = POST_OFFERS_URL + "?";
    private static final String CONTENT_LANGUAGE    = "en-US";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public OfferService (){

    }

    public Offer offerBuilder(EbayLocalOffer ebayoffer){
        Offer           offer = new Offer();
        Amount  amount = new Amount();
        PricingSummary pricingSummary = new PricingSummary();
        ListingPolicies listingPolicies = new ListingPolicies();
        User user;
        user = ebayoffer.getLocalItem().getUser();

        amount.setValue(ebayoffer.getLocalItem().getField("value"));
        amount.setCurrency(ebayoffer.getLocalItem().getField("currencyCode"));
        pricingSummary.setPrice(amount);

        offer.setPricingSummary(pricingSummary);
        offer.setSku(ebayoffer.getLocalItem().getField("sku"));
        offer.setMerchantLocationKey(ebayoffer.getLocalItem().getField("merchantLocationKey"));
        offer.setCategoryId(ebayoffer.getLocalItem().getField("categoryId"));
        offer.setFormat(ebayoffer.getLocalItem().getField("format"));
        offer.setMarketplaceId(ebayoffer.getLocalItem().getField("marketplaceId"));

        listingPolicies.setFulfillmentPolicyId(user.getFulfillmentPolicy());
        listingPolicies.setPaymentPolicyId(user.getPayementPolicy());
        listingPolicies.setReturnPolicyId(user.getReturnPolicy());

        offer.setListingPolicies(listingPolicies);

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
        OfferResponse       offerResponse;
        String              response;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<Offer>   httpEntity;

        System.err.println("Creating headers");
        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);

        httpEntity   = new HttpEntity<>(offer, httpHeaders);
        restTemplate = new RestTemplate();
        System.err.println("set error handler");
        restTemplate.setErrorHandler(new CreateOfferHandler());
        try {
            offerResponse = restTemplate.postForObject(POST_OFFERS_URL, httpEntity, OfferResponse.class);
            assert(offerResponse != null);
            response = offerResponse.getOfferId();
            System.err.println(response);
        }
        catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = "Could not create offer\n" + ex.getMessage();
            System.err.println(response);
        }
        catch (RestClientException e) {
            System.err.println(e.getMessage());
            throw e;
        }
        return response;
    }


    /**
     * Response handler.
     */
    public static class CreateOfferHandler
            implements ResponseErrorHandler
    {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) {
            boolean     hasError;
            try {
                hasError = !clientHttpResponse.getStatusCode().is2xxSuccessful();
            }
            catch (IOException e) {
                throw new ResourceAccessException("IO Exception reading clientHttpResponse");
            }
            return hasError;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            /* ToDo: Implement */
            System.err.println("HANDLE ERROR()");
        }
    }
}
