package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayLocalOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.*;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.*;
import com.scriptofan.ecommerce.Platforms.Ebay.GenericEbayErrorHandler;
import com.scriptofan.ecommerce.User.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.io.IOException;
import java.util.Scanner;


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
    public EbayRemoteOffer buildEbayOffer(EbayLocalOffer ebayoffer) {
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
     * @param offer EbayRemoteOffer to send to eBay.
     * @param token AccessToken to authorize the request with.
     * @return String with status.
     *
     * @throws EbayCreateOfferException
     */
    public String createOrUpdateOffer(EbayRemoteOffer offer, String token)
            throws EbayCreateOfferException, BadEbayTokenException, Ebay500ServerException, OfferAlreadyExistsException {
        String response;
        try {
            response = createOrUpdateOffer(offer, token, false, null);
        }
        catch (OfferAlreadyExistsException e) {
            System.err.println("OFFER ALREADY EXISTS! HERE");
            response = e.getMessage();
            createOrUpdateOffer(offer, token, true, response);
        }
        return response;
    }


    /*
     * Internal helper method. This lets us call either
     */
    private String createOrUpdateOffer(EbayRemoteOffer offer, String token, boolean update, String offerId)
            throws  BadEbayTokenException,
                    Ebay500ServerException,
                    OfferAlreadyExistsException,
                    EbayCreateOfferException
    {
        int                         retries;
        OfferResponse               offerResponse;
        RestTemplate                restTemplate;
        HttpHeaders                 httpHeaders;
        HttpMethod                  method;
        HttpEntity<EbayRemoteOffer> httpEntity;
        String                      response = null;

        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);

        // Ebay is flaky, so try a couple of times
        retries = 3;
        while (response == null && retries > 0)
        {
            System.err.println("Try " + retries);
            retries--;
            httpEntity = new HttpEntity<>(offer, httpHeaders);
            restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new CreateOfferHandler());

            try {
                if (update) {
                    restTemplate.put(POST_OFFERS_URL + "/" + offerId, httpEntity);
                    response = "success";
                }
                else {
                    offerResponse = restTemplate.postForObject(POST_OFFERS_URL, httpEntity, OfferResponse.class);
                    response      = offerResponse.getOfferId();
                }
            }
            catch (ResourceAccessException ex) {
                Throwable rootEx = ex.getRootCause();
                System.err.println("Exception:  " + ex);
                System.err.println("Root Cause: " + rootEx);

                // User is unauthorized
                if (rootEx instanceof BadEbayTokenException) {
                    throw (BadEbayTokenException) rootEx;
                }
                // Some sort of server error happened. Retry.
                else if (rootEx instanceof Ebay500ServerException) {
                    System.err.println("500 server error. Retry.");
                    if (retries == 0) {
                        throw (Ebay500ServerException) rootEx;
                    }
                    response = null;
                }
                // The offer already exists. We should update the offer instead.
                else if (rootEx instanceof OfferAlreadyExistsException) {
                    if (update == false) {
                        response = rootEx.getMessage();
                        throw (OfferAlreadyExistsException) rootEx;
                    }
                    else {
                        throw new EbayCreateOfferException(rootEx.getMessage());
                    }
                }
            }
        }

        if (response == null) {
            throw new EbayCreateOfferException("No response received.");
        }
        System.err.println(response);
        return response;
    }


    /**
     * Error response handler. At the time of writing, this is used mostly
     * for debugging.
     */
    public static class CreateOfferHandler extends GenericEbayErrorHandler
    {
        public static final String OFFER_ID_TEXT = "\"name\":\"offerId\",\"value\":\"";

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            loadErrorDetails(clientHttpResponse);

            int errorId = getEbayErrorId();
            if (errorId == 25002) {
                String offerId = parseOfferId();
                throw new IOException(new OfferAlreadyExistsException(offerId));
            }
            else if (errorId == 25702) {
                // SKU not found. CreateInventoryItem must have failed and we didn't catch it earlier.
                throw new IOException(new EbayCreateOfferException(getErrorString()));
            }

            super.handleError(clientHttpResponse);
            throw new IOException(getEbayMessage());
        }

        private String parseOfferId() {
            return parse(OFFER_ID_TEXT, "\"");
        }
    }

    public static class UpdateOfferErrorHandler extends GenericEbayErrorHandler {

    }
}
