package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.Ebay.Location.Location;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    private static final String GET_LOCATIONS_URL   = "https://api.sandbox.ebay.com/sell/inventory/v1/location";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public LocationService() {
    }

    /*public Location[] getInventoryLocations(String token) {

        Location[]           inventoryLocations;
        RestTemplate                restTemplate;
        HttpHeaders                 httpHeaders;
        HttpEntity<String>          httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

//        inventoryLocations = restTemplate
//                .exchange(
//                        GET_LOCATIONS_URL,
//                        HttpMethod.GET,
//                        httpEntity,
//                        GetLocationsRequestWrapper.class)
//                .getBody()
//                .getLocations();

        return null;
    }*/

    public String postLocation(Location location, String token) {

        String              merchantLocationId;
        String              completeUrl;
        String              response;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<String>  httpEntity;

        merchantLocationId  = sanitizeSpaces(location.getName());
        completeUrl         = GET_LOCATIONS_URL + "/" + merchantLocationId;

        httpHeaders         = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity          = new HttpEntity<>("parameters", httpHeaders);

        restTemplate        = new RestTemplate();
        try {
            restTemplate.exchange(completeUrl, HttpMethod.POST, httpEntity, Location.class);
        } catch (HttpServerErrorException ex) {
            ex.printStackTrace();
            response = ex.getMessage();
        }

        return response;
    }

    private String sanitizeSpaces(String str) {
        return str.replace(" ", "-");
    }

}
