package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Listing.EbayListing;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.BadEbayTokenException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.Ebay500ServerException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.EbayPublishOfferException;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.ListingAlreadyExistsException;
import com.scriptofan.ecommerce.Platforms.Ebay.GenericEbayErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This class publishes an EbayOffer to eBay and returns the
 * listing ID associated with it.
 */
public class EbayPublishOfferService {

    private static final String PUBLISH_OFFER_URI   = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/";
    private static final String URI_POSTFIX         = "/publish/";
    private static final String TOKEN_PREFIX        = "Bearer ";
    private static final String CONTENT_LANGUAGE    = "en-US";

    public static String publishEbayOffer(String offerId, String token)
            throws EbayPublishOfferException, ListingAlreadyExistsException {
        String              listingId;
        EbayListing         ebayListing = null;

        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<String>  httpEntity;

        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new PublishOfferHandler());

        httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization",    TOKEN_PREFIX + token);
        httpHeaders.set("Content-Language", CONTENT_LANGUAGE);
        httpHeaders.set("Accept",           "application/json");
        httpHeaders.set("Content-Type",     "application/json");

        httpEntity = new HttpEntity<>(offerId, httpHeaders);

        try {
            ebayListing = restTemplate.exchange(
                    PUBLISH_OFFER_URI + offerId + URI_POSTFIX,
                    HttpMethod.POST,
                    httpEntity,
                    EbayListing.class).getBody();
            listingId = ebayListing.toString();
        }
        catch (HttpServerErrorException ex) {
            throw new EbayPublishOfferException(ex);
        }
        catch (ResourceAccessException e) {
            Throwable rootEx = e.getRootCause();

            // Ebay had a server error. Retry.
            if (rootEx instanceof ListingAlreadyExistsException) {
                throw (ListingAlreadyExistsException) rootEx;
            }
            else {
                throw e;
            }
        }
        catch (NullPointerException e) {
            if (null == ebayListing) {
                throw new EbayPublishOfferException("Publishing EbayRemoteOffer returned null");
            }
            else {
                throw new EbayPublishOfferException(e);
            }
        }

        return listingId;
    }

    /**
     * Error handler.
     */
    public static class PublishOfferHandler extends GenericEbayErrorHandler
    {
        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            loadErrorDetails(clientHttpResponse);

            if (getEbayErrorId() == 25002) {
                throw new IOException(new ListingAlreadyExistsException(getEbayMessage()));
            }
        }
    }
}
