package com.scriptofan.ecommerce.Ebay.AccountPolicies.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetFulfillmentPolicies {

    private FulfillmentPolicy[] fulfillmentPolicies;

    public GetFulfillmentPolicies() {
    }

    public FulfillmentPolicy[] getFulfillmentPolicies() {
        return fulfillmentPolicies;
    }

    public void setFulfillmentPolicies(FulfillmentPolicy[] fulfillmentPolicies) {
        this.fulfillmentPolicies = fulfillmentPolicies;
    }
}
