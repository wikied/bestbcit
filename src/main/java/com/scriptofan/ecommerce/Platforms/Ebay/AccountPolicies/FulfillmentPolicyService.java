package com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies;

import com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies.Entity.FulfillmentPolicy;
import com.scriptofan.ecommerce.Platforms.Ebay.AccountPolicies.Entity.FulfillmentPoliciesWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FulfillmentPolicyService {

    private static final String GET_FULFILLMENT_POLICIES_URL    = "https://api.sandbox.ebay.com/sell/account/v1/fulfillment_policy?marketplace_id=EBAY_US";
    private static final String TOKEN_PREFIX                    = "Bearer ";

    /**
     * Retrieves an array of FulfillmentPolicy items from ebay, associated with
     * the user associated with token.
     *
     * @param token Token associated with target Ebay user.
     * @return Array of FulfillmentPolicy objects.
     */
    public FulfillmentPolicy[] getFulfillmentPolicies(String token) {

        FulfillmentPolicy[]     fulfillmentPolicies;
        FulfillmentPoliciesWrapper fulfillmentPolicyWrapper;
        RestTemplate            restTemplate;
        HttpHeaders             httpHeaders;
        HttpEntity<String>      httpEntity;

        restTemplate    = new RestTemplate();
        httpHeaders     = new HttpHeaders();
        httpHeaders.set("authorization", TOKEN_PREFIX + token);
        httpEntity      = new HttpEntity<>("parameters", httpHeaders);

        fulfillmentPolicies = restTemplate
                .exchange(
                    GET_FULFILLMENT_POLICIES_URL,
                    HttpMethod.GET,
                    httpEntity,
                    FulfillmentPoliciesWrapper.class)
                .getBody()
                .getFulfillmentPolicies();

        return fulfillmentPolicies;
    }

}
