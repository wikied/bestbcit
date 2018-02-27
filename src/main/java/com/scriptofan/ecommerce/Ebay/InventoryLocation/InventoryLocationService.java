package com.scriptofan.ecommerce.Ebay.InventoryLocation;

import com.scriptofan.ecommerce.Ebay.AccountPolicies.Entity.FulfillmentPoliciesWrapper;
import com.scriptofan.ecommerce.Ebay.AccountPolicies.Entity.FulfillmentPolicy;
import com.scriptofan.ecommerce.Ebay.InventoryLocation.Entity.GetLocationsRequestWrapper;
import com.scriptofan.ecommerce.Ebay.InventoryLocation.Entity.InventoryLocation;
import com.scriptofan.ecommerce.Ebay.InventoryLocation.Entity.LocationWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryLocationService {

    private static final String GET_LOCATIONS_URL   = "https://api.sandbox.ebay.com/sell/inventory/v1/location";
    private static final String TOKEN_PREFIX        = "Bearer ";

    public InventoryLocationService() {
    }

    public LocationWrapper[] getInventoryLocations(String token) {

        LocationWrapper[]           inventoryLocations;
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
                        GetLocationsRequestWrapper.class)
                .getBody()
                .getLocations();

        return inventoryLocations;
    }
}
