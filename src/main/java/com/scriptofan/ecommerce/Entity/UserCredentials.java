package com.scriptofan.ecommerce.Entity;

public class UserCredentials {

    private User    associatedUser;

    public UserCredentials(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }
}
