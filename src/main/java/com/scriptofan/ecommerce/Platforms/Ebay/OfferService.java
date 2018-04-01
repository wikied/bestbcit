package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayCreateOfferException;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.*;
import com.scriptofan.ecommerce.User.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.IOException;


/**
 * Responsible for converting EbayLocalOffers to EbayRemoteOffers and posting
 * these on eBay.
 */
@Service
public class OfferService {

    private static final String POST_OFFERS_URL     = "https://api.sandbox.ebay.com/sell/inventory/v1/offer";
    private static final String GET_OFFERS_URL      = POST_OFFERS_URL + "?";
    private static final String CONTENT_LANGUAGE    = "en-US";
    private static final String TOKEN_PREFIX        = "Bearer ";

    /**
     * Blank default constructor.
     */
    public OfferService () { }


    /**
     * Constructs a new EbayRemoteOffer (POJO that maps to the JSON eBay needs
     * to represent an offer on their servers). It does this by reading the
     * fields contained in an EbayLocalOffer (our local representation, that
     * maps to how we store data).
     *
     * @param ebayoffer Source EbayLocalOffer to map to EbayRemoteOffer.
     * @return EbayRemoteOffer, based on provided EbayLocalOffer.
     */
    public EbayRemoteOffer buildEbayOffer(EbayLocalOffer ebayoffer){
        EbayRemoteOffer ebayRemoteOffer     = new EbayRemoteOffer();
        Amount          amount              = new Amount();
        PricingSummary  pricingSummary      = new PricingSummary();
        ListingPolicies listingPolicies     = new ListingPolicies();
        User            user                = ebayoffer.getLocalItem().getUser();

        amount.setValue(ebayoffer.getLocalItem().getField("value"));
        amount.setCurrency(ebayoffer.getLocalItem().getField("currencyCode"));

        pricingSummary.setPrice(amount);

        ebayRemoteOffer.setPricingSummary(pricingSummary);
        ebayRemoteOffer.setSku(ebayoffer.getLocalItem().getField("sku"));
        ebayRemoteOffer.setMerchantLocationKey(ebayoffer.getLocalItem().getField("merchantLocationKey"));
        ebayRemoteOffer.setCategoryId(ebayoffer.getLocalItem().getField("categoryId"));
        ebayRemoteOffer.setFormat(ebayoffer.getLocalItem().getField("format"));
        ebayRemoteOffer.setMarketplaceId(ebayoffer.getLocalItem().getField("marketplaceId"));

        listingPolicies.setFulfillmentPolicyId(user.getFulfillmentPolicy());
        listingPolicies.setPaymentPolicyId(user.getPayementPolicy());
        listingPolicies.setReturnPolicyId(user.getReturnPolicy());

        ebayRemoteOffer.setListingPolicies(listingPolicies);

        return ebayRemoteOffer;
    }


    /**
     * Performs a REST call to eBay and returns a set of offers. The offers
     * returned are those belonging to the owner of the provided access token.
     *
     * @param token Access token to pass to eBay.
     * @return Array of EbayRemoteOffers.
     */
    public EbayRemoteOffer[] getOffers(String token){
        EbayRemoteOffer[]   itemEbayRemoteOffers;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<String>  httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

        itemEbayRemoteOffers = restTemplate
                .exchange(
                        GET_OFFERS_URL,
                        HttpMethod.GET,
                        httpEntity,
                        EbayRemoteOffer[].class)
                .getBody();

        return itemEbayRemoteOffers;
    }


    /**
     * Performs a REST call to eBay, creating a remote offer on their server.
     * The specific offer is determined by the provided EbayRemoteOffer. The
     * account these are associated with are determined by the provided access
     * token.
     *
     * @param ebayRemoteOffer EbayRemoteOffer to send to eBay.
     * @param token AccessToken to authorize the request with.
     * @return String with status.
     *
     * @throws EbayCreateOfferException
     */
    public String createOffer(EbayRemoteOffer ebayRemoteOffer, String token)
            throws EbayCreateOfferException
    {
        OfferResponse               offerResponse;
        String                      response;
        RestTemplate                restTemplate;
        HttpHeaders                 httpHeaders;
        HttpEntity<EbayRemoteOffer> httpEntity;

        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);

        httpEntity   = new HttpEntity<>(ebayRemoteOffer, httpHeaders);
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CreateOfferHandler());

        try {
            offerResponse = restTemplate.postForObject(
                    POST_OFFERS_URL,
                    httpEntity,
                    OfferResponse.class);

            assert(offerResponse != null);
            response = offerResponse.getOfferId();

            System.err.println(response);
        }
        catch (HttpServerErrorException ex) {
            throw new EbayCreateOfferException(ex);
        }

        return response;
    }


    /**
     * Error response handler. At the time of writing, this is used mostly
     * for debugging.
     */
    public static class CreateOfferHandler
            implements ResponseErrorHandler
    {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) {

            boolean hasError;
            try {
                hasError = !clientHttpResponse
                        .getStatusCode()
                        .is2xxSuccessful();
            }
            catch (IOException e) {
                throw new ResourceAccessException("IO Exception reading clientHttpResponse");
            }
            return hasError;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse)
                throws IOException
        {
            // TODO: Implement
        }
    }
}
