package com.scriptofan.ecommerce.Entity;

/**
 * A Scriptofan Ecommerce User. Aggregates all components of a user.
 */
public class User {

    // User ID
    private String                  uuid;

    // User Components
    private UserCredentials         credentials;
    private UserProfile             profile;
    private Inventory               inventory;
    private PlatformAccountManager  platformAccountManager;

    // Constructors
    public User() {}
    public User(String uuid) {
        this.uuid = uuid;
    }
}
