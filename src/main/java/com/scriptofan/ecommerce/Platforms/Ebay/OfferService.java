package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Amount;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.OfferId;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.PricingSummary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
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

    public Offer offerBuilder(EbayLocalLocalOffer ebayoffer){
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

        OfferId offerId;
        String                 response;
        RestTemplate       restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<Offer>    httpEntity;

        httpHeaders         = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);
        httpEntity = new HttpEntity<>(offer, httpHeaders);

        restTemplate        = new RestTemplate();
        restTemplate.setErrorHandler(new CreateOfferHandler());

        for (HttpMessageConverter m : restTemplate.getMessageConverters()) {
            System.err.println(m);
        }

        try {
            System.err.println("MAKING CREATE OFFER CALL");

            offerId = restTemplate.exchange(POST_OFFERS_URL, HttpMethod.POST, httpEntity, OfferId.class).getBody();

            System.err.println("OFFER RESPONSE: ");
            assert(offerId != null);
            System.err.println("Offer Response: " + offerId.getOfferId());

            // response = offerId.getOfferId();
            return "hi";
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = "Could not create offer\n" + ex.getMessage();
        } catch (RestClientException e) {
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
            HttpStatus          statusCode;

            System.err.println("HAS ERROR()");

            try {
                /* Handle response code */
                statusCode = clientHttpResponse.getStatusCode();

                if (statusCode.is2xxSuccessful()) {
                    System.err.println("All good");
                    // All clear
                } else {
                    System.err.println("Some other error");
                }
            }
            catch (IOException e) {
                throw new ResourceAccessException("IO Exception reading clientHttpResponse");
            }

            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            /* ToDo: Implement */
            System.err.println("HANDLE ERROR()");
        }
    }
}
