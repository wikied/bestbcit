package com.scriptofan.ecommerce.Platforms.Ebay.Entity.AccountPolicy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentPolicy {

    private String name;

    public FulfillmentPolicy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
