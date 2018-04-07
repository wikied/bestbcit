package com.scriptofan.ecommerce.User;

import com.scriptofan.ecommerce.DummyEbayRequestData;

/**
 * Represents a user of this application. This is currently
 * basically just a placeholder object, and will need to be
 * filled out further.
 */
public class User {
    private String userID;
    private String token;
    public User(){}
    public User(String userID){
        this.userID = userID;
    }

    public String getUserToken() {
        return DummyEbayRequestData.ebayOAuthToken;
    }
    public String getPayementPolicy() {return DummyEbayRequestData.PAYMENT_POLICY;}
    public String getReturnPolicy() {return DummyEbayRequestData.RETURN_POLICY;}
    public String getFulfillmentPolicy() {return  DummyEbayRequestData.FULFILLMENT_POLICY;}
}
