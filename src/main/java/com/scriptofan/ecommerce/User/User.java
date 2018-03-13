package com.scriptofan.ecommerce.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scriptofan.ecommerce.Database.Item;
import com.scriptofan.ecommerce.DummyRequestData;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    private String name;

    @Transient
    private String token;

    public User(){}


    public User(String name){
        this.name = name;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }

    @JsonIgnore
    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public String getUserToken() {
        return DummyRequestData.TOKEN;
    }

    @JsonIgnore
    public String getPayementPolicy() {return DummyRequestData.PAYMENT_POLICY;}
    @JsonIgnore
    public String getReturnPolicy() {return DummyRequestData.RETURN_POLICY;}
    @JsonIgnore
    public String getFulfillmentPolicy() {return  DummyRequestData.FULFILLMENT_POLICY;}

}
