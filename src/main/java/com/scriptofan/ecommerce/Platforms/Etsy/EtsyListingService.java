package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.DummyEtsyOAuthHeaderGen;

import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

@Service
public class EtsyListingService {
    public static final String ETSY_POST_URL = "https://openapi.etsy.com/v2/listings";


    /**
     * Makes a createListing call to the Etsy API, passing the specified LocalOffer.
     *
     * @param etsyLocalOffer LocalOffer to attempt to post to Etsy.
     * @throws MalformedURLException URL is malformed
     * @throws UnsupportedEncodingException
     */
    public void creatingListing(EtsyLocalOffer etsyLocalOffer)
            throws MalformedURLException, RulesetViolationException
    {
        UriComponentsBuilder            uriComponentsBuilder;
        RestTemplate                    restTemplate;
        String                          completeURL;
        HttpMethod                      httpMethod  = HttpMethod.POST;
        HttpHeaders                     httpHeaders;
        HttpEntity<Void>                httpEntity;
        DummyEtsyOAuthHeaderGen         dummyEtsyOAuthHeaderGen;

        httpHeaders                 = new HttpHeaders();
        dummyEtsyOAuthHeaderGen     = new DummyEtsyOAuthHeaderGen();
        uriComponentsBuilder        = UriComponentsBuilder.fromUriString(ETSY_POST_URL);

        for (EtsyListingBuilderRule rule : EtsyListingBuilderRuleset.RULES) {
            String value;
            String keyInternal;
            String keyOnEtsy;

            keyInternal = rule.getKeyInternal();
            keyOnEtsy   = rule.getKeyOnEtsy();
            value       = etsyLocalOffer.getLocalItem().getField(keyInternal);

            if (rule.validate(value) && value != null) {
                uriComponentsBuilder = uriComponentsBuilder
                        .queryParam(keyOnEtsy, value);
            }
        }

        // Set quantity parameter for API call
        uriComponentsBuilder        = uriComponentsBuilder.queryParam("quantity", etsyLocalOffer.getQuantity());

        // Build URL and compute and append OAuth headers
        completeURL                 = uriComponentsBuilder.build().toUriString();
        httpHeaders                 = dummyEtsyOAuthHeaderGen.addEtsyAuthorizationHeader(httpHeaders, completeURL, httpMethod);
        httpEntity                  = new HttpEntity(null, httpHeaders);

        try {
            restTemplate                = new RestTemplate();
            restTemplate.setErrorHandler(new CreateListingResponseHandler());
            restTemplate.exchange(completeURL, httpMethod, httpEntity, String.class);
        }
        catch(ResourceAccessException e){
            System.err.println("Caught Resource access Exception");
            e.getCause().printStackTrace();
            System.exit(1);
        }

    }


    /**
     * Handles errors in the post-to-etsy API call.
     */
    private class CreateListingResponseHandler implements ResponseErrorHandler{

        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            return !clientHttpResponse.getStatusCode().is2xxSuccessful();
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            // Extract error body
            String          line;
            BufferedReader  bufferReader;
            StringBuilder   stringBuilder;

            try {
                bufferReader    = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
                stringBuilder   = new StringBuilder();

                while ((line = bufferReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                // Print results to err log
                System.err.println("clientHttpResponse error:");
                System.err.println(clientHttpResponse.getStatusCode() + " " + clientHttpResponse.getStatusText());
                System.err.println(stringBuilder.toString());
            }
            catch(IOException e){
                System.err.println("COULDN'T READ BODY");
            }
        }
    }


    /*
     * Converts an HttpHeaders object's contents to a string.
     */
    private String headersToString(HttpHeaders headers) {
        String output = "";

        for (Map.Entry<String, List<String>> entry : headers.entrySet()){
            output += entry.getKey() + "=";
            for (String s : entry.getValue()) {
                output += s + ", ";
            }
            output += "\n";
        }
        return output;
    }
}
