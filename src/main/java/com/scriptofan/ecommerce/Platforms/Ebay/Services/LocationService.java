package com.scriptofan.ecommerce.Platforms.Ebay.Services;

import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Location.Location;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class LocationService {

    private static final String GET_LOCATIONS_URL   = "https://api.sandbox.ebay.com/sell/inventory/v1/location";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public LocationService() {
    }

    public Location[] getInventoryLocations(String token) {

        Location[]           inventoryLocations;
        RestTemplate                restTemplate;
        HttpHeaders                 httpHeaders;
        HttpEntity<String>          httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

        inventoryLocations = restTemplate
                .exchange(
                        GET_LOCATIONS_URL,
                        HttpMethod.GET,
                        httpEntity,
                        Location[].class)
                .getBody();

        return inventoryLocations;
    }

    public String createInventoryLocation(Location location, String token) {

        String              merchantLocationId;
        String              completeUrl;
        String              response;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<Location>  httpEntity;

        merchantLocationId  = sanitizeSpaces(location.getName());
        completeUrl         = GET_LOCATIONS_URL + "/" + merchantLocationId;

        httpHeaders         = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpHeaders.set("Content-Type", "application/json");
        httpEntity = new HttpEntity<>(location, httpHeaders);

        restTemplate        = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());

        try {
            restTemplate.exchange(completeUrl, HttpMethod.POST, httpEntity, Location.class);
            response = "success";
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }

        return response;
    }

    public static class MyErrorHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            // Extract error body
            String          line;
            BufferedReader  bufferReader;
            StringBuilder   stringBuilder;

            bufferReader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody()));
            stringBuilder = new StringBuilder();

            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            // Print results to err log
            System.err.println("clientHttpResponse error:");
            System.err.println(clientHttpResponse.getStatusCode() + " " + clientHttpResponse.getStatusText());
            System.err.println(stringBuilder.toString());
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException { }
    }

    private String sanitizeSpaces(String str) {
        return str.replace(" ", "-");
    }

}
