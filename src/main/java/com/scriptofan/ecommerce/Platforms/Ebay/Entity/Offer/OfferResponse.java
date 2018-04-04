package com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * The response from a successful create offer which is
 * the offer id
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResponse {

    // Unique id when a offer is successfully created
    @JsonProperty("offerId")
    private String offerId;

    @JsonGetter("offerId")
    public String getOfferId() {
        return offerId;
    }

    @JsonSetter("offerId")
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
