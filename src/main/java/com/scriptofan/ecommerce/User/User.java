package com.scriptofan.ecommerce.User;

import com.scriptofan.ecommerce.Database.Item;
import com.scriptofan.ecommerce.DummyRequestData;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userID;

    private String name;

    private String token;

//    @ManyToOne
//    private Item item;

    public User(){}


    public User(String name){
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserToken() {
        return DummyRequestData.TOKEN;
    }
    public String getPayementPolicy() {return DummyRequestData.PAYMENT_POLICY;}
    public String getReturnPolicy() {return DummyRequestData.RETURN_POLICY;}
    public String getFulfillmentPolicy() {return  DummyRequestData.FULFILLMENT_POLICY;}

}
