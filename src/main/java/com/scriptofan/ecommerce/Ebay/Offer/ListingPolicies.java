package com.scriptofan.ecommerce.Ebay.Offer;

// Contains the identifiers of the payment, return and fulfillment
// polices that are to be associated with the offer
public class ListingPolicies {

    // Unique identifier for the sellers payment policy that will be
    // used once the offer is published
    String paymentPolicyId;

    // Unique identifier for the sellers return policy that will be
    // used once the offer is published
    String returnPolicyId;

    // Unique identifier for the sellers fulfillment policy that will be
    // used once the offer is published
    String fulfillmentPolicyId;

    public String getPaymentPolicyId() {
        return paymentPolicyId;
    }

    public void setPaymentPolicyId(String paymentPolicyId) {
        this.paymentPolicyId = paymentPolicyId;
    }

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getFulfillmentPolicyId() {
        return fulfillmentPolicyId;
    }

    public void setFulfillmentPolicyId(String fulfillmentPolicyId) {
        this.fulfillmentPolicyId = fulfillmentPolicyId;
    }

}
