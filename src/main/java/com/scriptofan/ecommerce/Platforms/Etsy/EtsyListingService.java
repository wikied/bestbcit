package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Platforms.Etsy.Listing.Listing;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class EtsyListingService {
    public static final String ETSY_POST_URL = "https://openapi.etsy.com/v2/listings";

    public void creatingListing(EtsyLocalOffer etsyLocalOffer, String token){
        String                          response;
        UriComponentsBuilder            uriComponentsBuilder;
        RestTemplate                    restTemplate;
        RequestEntity                   requestEntity;
        HttpHeaders                     httpHeaders;
        HttpEntity<EtsyLocalOffer>      httpEntity;

        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        uriComponentsBuilder = UriComponentsBuilder.fromUriString(ETSY_POST_URL);
        httpHeaders.set("authorization", token);
        httpHeaders.set("title", "MyTemplate");
        httpHeaders.set("origin_country_id", "79");
        httpHeaders.set("primary_cost", "5");
        httpHeaders.set("secondary_cost", "2.50");
        httpEntity = new HttpEntity(etsyLocalOffer, httpHeaders);

        for(Map.Entry<String, String> entry : etsyLocalOffer.getLocalItem().getAllFields().entrySet()) {
            uriComponentsBuilder = UriComponentsBuilder.fromUriString(ETSY_POST_URL)
                    //Adding the parameters
                    .queryParam(entry.getKey(), entry.getValue());
        }

        restTemplate.exchange(uriComponentsBuilder.build().toUri() , HttpMethod.PUT,
                httpEntity, Listing.class);

    }
}
