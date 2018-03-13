package com.scriptofan.ecommerce.User;

import com.scriptofan.ecommerce.DummyRequestData;

public class User {
    private String userID;
    private String token;
    public User(){}
    public User(String userID){
        this.userID = userID;
    }

    public String getUserToken() {
        return DummyRequestData.TOKEN;
    }
    public String getPayementPolicy() {return DummyRequestData.PAYMENT_POLICY;}
    public String getReturnPolicy() {return DummyRequestData.RETURN_POLICY;}
    public String getFulfillmentPolicy() {return  DummyRequestData.FULFILLMENT_POLICY;}
}
