package com.scriptofan.ecommerce.Platforms.Ebay.Offer;

import com.scriptofan.ecommerce.Platforms.Ebay.EbayListing;
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

public class EbayPublishOffer {

    private static final String PUBLISH_OFFER_URI   = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/";
    private static final String URI_POSTFIX         = "/publish/";
    private static final String TOKEN_PREFIX        = "Bearer ";
    private static final String CONTENT_LANGUAGE    = "en-US";

    public static String publishEbayOffer(String offerId, String token) {
        String              response;
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

        response = "";
        try {
            ebayListing = restTemplate.exchange(PUBLISH_OFFER_URI + offerId + URI_POSTFIX,
                                   HttpMethod.POST, httpEntity, EbayListing.class).getBody();
            System.err.println(ebayListing);
            response += ebayListing.toString();
        }
        catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }
        catch (NullPointerException e) {
            if (null == ebayListing) {
                response += "Publishing Offer returned null";
            }
        }

        return response;
    }

    /**
     * Error handler.
     */
    public static class PublishOfferHandler
            implements ResponseErrorHandler
    {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) {
            boolean hasError;
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
            System.err.println("Starting publishEbayOffer.handleError()");

            HttpStatus          statusCode;
            String              statusText;
            String              output;
            String              line;
            BufferedReader      bufferReader;
            StringBuilder       stringBuilder;
            InputStreamReader   responseBodyReader;

            statusCode = clientHttpResponse.getStatusCode();
            statusText = clientHttpResponse.getStatusText();

            /* Generate debug output */
            stringBuilder       = new StringBuilder();
            responseBodyReader  = new InputStreamReader(clientHttpResponse.getBody());
            bufferReader        = new BufferedReader(responseBodyReader);

            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            output = "";
            output += "clientHttpResponse error:\n";
            output += statusCode + " " + statusText + "\n";
            output += stringBuilder.toString() + "\n";

            System.err.println(output);

            System.err.println("Done publishEbayOffer.handleError()");
        }
    }
}
