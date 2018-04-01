package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.DummyEtsyOAuthHeaderGen;
import com.scriptofan.ecommerce.Platforms.Etsy.Listing.Listing;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.util.Map;

@Service
public class EtsyListingService {
    public static final String ETSY_POST_URL = "https://openapi.etsy.com/v2/listings";

    public void creatingListing(EtsyLocalOffer etsyLocalOffer) throws MalformedURLException {

        UriComponentsBuilder            uriComponentsBuilder;
        RestTemplate                    restTemplate;
        String                          completeURL;
        HttpHeaders                     httpHeaders;
        HttpEntity<EtsyLocalOffer>      httpEntity;
        DummyEtsyOAuthHeaderGen         dummyEtsyOAuthHeaderGen;

        restTemplate                = new RestTemplate();
        httpHeaders                 = new HttpHeaders();

        dummyEtsyOAuthHeaderGen     = new DummyEtsyOAuthHeaderGen();

        uriComponentsBuilder        = UriComponentsBuilder.fromUriString(ETSY_POST_URL);

        for(Map.Entry<String, String> entry : etsyLocalOffer.getLocalItem().getAllFields().entrySet()) {
            uriComponentsBuilder = UriComponentsBuilder.fromUriString(ETSY_POST_URL)
                    //Adding the parameters
                    .queryParam(entry.getKey(), entry.getValue());
        }

        completeURL                 = uriComponentsBuilder.build().toUriString();
        httpHeaders                 = dummyEtsyOAuthHeaderGen.addEtsyAuthorizationHeader(httpHeaders, completeURL, HttpMethod.POST);
        httpEntity                  = new HttpEntity(etsyLocalOffer, httpHeaders);

        restTemplate.exchange(completeURL, HttpMethod.PUT,
                httpEntity, Listing.class);
    }
}
