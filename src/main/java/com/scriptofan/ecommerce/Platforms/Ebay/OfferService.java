package com.scriptofan.ecommerce.Platforms.Ebay;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;

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
public class OfferService {

    private static final String GET_OFFERS_URL   = "https://api.sandbox.ebay.com/sell/inventory/v1/offer?";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public OfferService (){

    }

    public Offer[] getOffers(String token){
        Offer[]             itemOffers;
        RestTemplate        restTemplate;
        HttpHeaders         httpHeaders;
        HttpEntity<String>  httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

        itemOffers = restTemplate
                .exchange(
                        GET_OFFERS_URL,
                        HttpMethod.GET,
                        httpEntity,
                        Offer[].class)
                .getBody();

        return itemOffers;

    }

    public String createOffer(Offer offer, String token){

    }

}
