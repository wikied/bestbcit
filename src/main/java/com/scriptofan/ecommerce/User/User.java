package com.scriptofan.ecommerce.User;

import com.scriptofan.ecommerce.DummyRequestData;

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
        return DummyRequestData.ebayOAuthToken;
    }
    public String getPayementPolicy() {return DummyRequestData.PAYMENT_POLICY;}
    public String getReturnPolicy() {return DummyRequestData.RETURN_POLICY;}
    public String getFulfillmentPolicy() {return  DummyRequestData.FULFILLMENT_POLICY;}
}
