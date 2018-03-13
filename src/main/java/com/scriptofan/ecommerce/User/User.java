package com.scriptofan.ecommerce.User;

import com.scriptofan.ecommerce.Database.Item;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    private String name;

//    @ManyToOne
//    private Item item;

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

}
