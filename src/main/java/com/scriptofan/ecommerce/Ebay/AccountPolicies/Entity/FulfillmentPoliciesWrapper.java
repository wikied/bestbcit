package com.scriptofan.ecommerce.Ebay.AccountPolicies.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Wrapper class for retrieving FulfillmentPolicy info from Ebay.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentPoliciesWrapper {

    private FulfillmentPolicy[] fulfillmentPolicies;

    public FulfillmentPoliciesWrapper() {
    }

    public FulfillmentPolicy[] getFulfillmentPolicies() {
        return fulfillmentPolicies;
    }

    public void setFulfillmentPolicies(FulfillmentPolicy[] fulfillmentPolicies) {
        this.fulfillmentPolicies = fulfillmentPolicies;
    }
}
