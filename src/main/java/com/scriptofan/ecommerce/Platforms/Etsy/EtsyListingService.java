package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.DummyEtsyOAuthHeaderGen;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
public class EtsyListingService {
    public static final String ETSY_POST_URL = "https://openapi.etsy.com/v2/listings";

    public void creatingListing(EtsyLocalOffer etsyLocalOffer) throws MalformedURLException, UnsupportedEncodingException {

        UriComponentsBuilder            uriComponentsBuilder;
        RestTemplate                    restTemplate;
        String                          completeURL;
        HttpMethod                      httpMethod  = HttpMethod.POST;
        HttpHeaders                     httpHeaders;
        HttpEntity<Void>                httpEntity;
        DummyEtsyOAuthHeaderGen         dummyEtsyOAuthHeaderGen;
        UriBuilder                      uriBuilder;


        restTemplate                = new RestTemplate();
        restTemplate.setErrorHandler(new DebugResponseHandler());

        httpHeaders                 = new HttpHeaders();
        dummyEtsyOAuthHeaderGen     = new DummyEtsyOAuthHeaderGen();
        uriComponentsBuilder        = UriComponentsBuilder.fromUriString(ETSY_POST_URL);

        for(Map.Entry<String, String> entry : etsyLocalOffer.getLocalItem().getAllFields().entrySet()) {
            uriComponentsBuilder = uriComponentsBuilder
                    //Adding the parameters
                    .queryParam(entry.getKey(), entry.getValue());
        }

        uriComponentsBuilder        = uriComponentsBuilder.queryParam("quantity", etsyLocalOffer.getQuantity());
        completeURL                 = uriComponentsBuilder.build().toUriString();
        httpHeaders                 = dummyEtsyOAuthHeaderGen.addEtsyAuthorizationHeader(httpHeaders, completeURL, httpMethod);
        httpEntity                  = new HttpEntity(null, httpHeaders);


        System.err.println("URL BEING SENT: " + completeURL);
        System.err.println("HEADERS:        " + headersToString(httpHeaders));


        try{
            restTemplate.exchange(completeURL, httpMethod, httpEntity, String.class);
        }
        catch(ResourceAccessException e){
            System.err.println("Caught Resource access Exception");
            e.getCause().printStackTrace();
            System.exit(1);
        }

    }

    private class DebugResponseHandler implements ResponseErrorHandler{

        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            return !clientHttpResponse.getStatusCode().is2xxSuccessful();
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            // Extract error body
            System.err.println("ANOTHER ERROR HERE");
            String          line;
            BufferedReader bufferReader;
            StringBuilder   stringBuilder;

            try {
                bufferReader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
                stringBuilder = new StringBuilder();


                while ((line = bufferReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                // Print results to err log
                System.err.println("clientHttpResponse error:");
                System.err.println(clientHttpResponse.getStatusCode() + " " + clientHttpResponse.getStatusText());
                System.err.println(stringBuilder.toString());
            }catch(IOException e){
                System.err.println("COULDN'T READ BODY");
            }
        }
    }

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
