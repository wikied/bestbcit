package com.scriptofan.ecommerce.Entity;

public class UserProfile {

    private String  firstName;
    private String  lastName;
    private String  email;
    private String  telNo;

    public UserProfile() {
        firstName   = "NOT A VALID USER";
        lastName    = "NOT A VALID USER";
        email       = "NO EMAIL ENTERED";
    }

    public UserProfile(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
