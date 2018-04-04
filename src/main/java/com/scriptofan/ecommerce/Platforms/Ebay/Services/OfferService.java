package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayLocalOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.*;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.*;
import com.scriptofan.ecommerce.Platforms.Ebay.GenericEbayErrorHandler;
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

    public static final int MAX_RETRIES             = 3;
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
            throws  EbayCreateOfferException,
                    BadEbayTokenException,
                    Ebay500ServerException
    {
        String offerId;
        try {
            offerId = createOffer(offer, token);
        }
        catch (OfferAlreadyExistsException e) {
            offerId = e.getMessage();
            updateOffer(offer, token, offerId);
        }
        return offerId;
    }


    private String createOffer(EbayRemoteOffer offer, String token)
            throws  EbayCreateOfferException,
                    OfferAlreadyExistsException,
                    Ebay500ServerException,
                    BadEbayTokenException
    {
        RestTemplate                    template;
        HttpHeaders                     headers;
        HttpEntity<EbayRemoteOffer>     entity;
        String                          offerId = null;

        headers     = buildHttpHeaders(token);
        entity      = buildHttpEntity(offer, headers);
        template    = buildRestTemplate();

        for (int i = 1; i <= MAX_RETRIES; ++i) {
            try {
                offerId = performCreateOffer(template, entity);
                if (offerId != null) {
                    break;
                }
            }
            catch (Ebay500ServerException e) {
                if (i > MAX_RETRIES) {
                    throw e;
                }
            }
        }

        if (offerId != null) {
            return offerId;
        } else {
            throw new EbayCreateOfferException();
        }
    }


    /*
     * Performs the createOffer call to eBay's API.
     */
    private String performCreateOffer(RestTemplate restTemplate, HttpEntity httpEntity)
            throws Ebay500ServerException, OfferAlreadyExistsException, BadEbayTokenException
    {
        OfferResponse offerResponse = null;

        try
        {
            offerResponse = restTemplate.postForObject(POST_OFFERS_URL, httpEntity, OfferResponse.class);
        }
        catch (ResourceAccessException e) {
            unpackResourceAccessException(e);
        }
        return offerResponse.getOfferId();
    }


    /*
     * Sequences the process of calling eBay's updateOffer endpoint.
     */
    private void updateOffer(EbayRemoteOffer offer, String token, String offerId)
            throws BadEbayTokenException, Ebay500ServerException
    {
        RestTemplate                    template;
        HttpHeaders                     headers;
        HttpEntity<EbayRemoteOffer>     entity;

        headers     = buildHttpHeaders(token);
        entity      = buildHttpEntity(offer, headers);
        template    = buildRestTemplate();

        for (int i = 1; i <= MAX_RETRIES; ++i) {
            try
            {
                performUpdateOffer(template, entity, offerId);
                i = MAX_RETRIES; // Break
            }
            catch (Ebay500ServerException e) {
                if (i > MAX_RETRIES) {
                    throw e;
                }
            }
        }
    }


    /*
     * Performs the actual call to ebay's updateOffer endpoint.
     */
    private void performUpdateOffer(RestTemplate restTemplate, HttpEntity httpEntity, String offerId)
            throws Ebay500ServerException, BadEbayTokenException
    {
        try
        {
            restTemplate.put(POST_OFFERS_URL + "/" + offerId, httpEntity);
        }
        catch (ResourceAccessException e) {
            try {
                unpackResourceAccessException(e);
            }
            catch (OfferAlreadyExistsException ex) {
                /* This will never happen. */
            }
        }
    }


    /*
     * Builds the HTTP Headers for PUT and POST requests to
     * createOffer and performUpdateOffer.
     */
    private HttpHeaders buildHttpHeaders(String token) {
        HttpHeaders httpHeaders;
        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);
        return httpHeaders;
    }


    /*
     * Constructs an HttpEntity object. Encapsulated to keep things
     * in the sequencing methods more readable.
     */
    private HttpEntity<EbayRemoteOffer> buildHttpEntity(EbayRemoteOffer offer, HttpHeaders httpHeaders) {
        HttpEntity<EbayRemoteOffer> httpEntity = new HttpEntity<>(offer, httpHeaders);
        return httpEntity;
    }


    /*
     * Constructs a RestTemplate. This is usable in either createOffer
     * or publishOffer.
     */
    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate;
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CreateOfferHandler());
        return restTemplate;
    }


    /*
     * Unpacks the root exceptions contained in a ResourceAccessException.
     *
     * NOTE: This is definitely an ugly hack. It was implemented because,
     * at the time of writing, handling errors in the CreateOfferHandler
     * seemed unfeasible (early April, 2018 ~ Patrick Charles-Lundaahl).
     *
     * Basically, there's a huge variety of different things that could
     * go wrong. In our current logic, they all need to be dealt with at
     * different levels, and ResponseErrorHandler subclasses cannot throw
     * anything except IOExceptions from handleError() or hasError().
     */
    private void unpackResourceAccessException(ResourceAccessException ex)
            throws BadEbayTokenException, Ebay500ServerException, OfferAlreadyExistsException
    {
        Exception rootEx;
        if (ex.getRootCause() instanceof Exception) {
            rootEx = (Exception) ex.getRootCause();

            // User is unauthorized
            if (rootEx instanceof BadEbayTokenException) {
                throw (BadEbayTokenException) rootEx;
            }
            // Some sort of server error happened. Retry.
            else if (rootEx instanceof Ebay500ServerException) {
                throw (Ebay500ServerException) rootEx;
            }
            // The offer already exists. We should update the offer instead.
            else if (rootEx instanceof OfferAlreadyExistsException) {
                throw (OfferAlreadyExistsException) rootEx;
            }
            else {
                throw ex;
            }
        }

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
