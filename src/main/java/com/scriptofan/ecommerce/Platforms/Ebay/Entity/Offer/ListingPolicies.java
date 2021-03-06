package com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer;

/** Contains the identifiers of the payment, return and fulfillment
*   polices that are to be associated with the offer
 **/
public class ListingPolicies {

    // Unique identifier for the sellers payment policy that will be
    // used once the offer is published
    private String paymentPolicyId;

    // Unique identifier for the sellers return policy that will be
    // used once the offer is published
    private String returnPolicyId;

    // Unique identifier for the sellers fulfillment policy that will be
    // used once the offer is published
    private String fulfillmentPolicyId;

    public ListingPolicies() {

    }

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
