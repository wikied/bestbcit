package com.scriptofan.ecommerce.Platforms.Etsy.Payment;

public class PaymentTemplate {
    private int payment_template_id;
    private boolean allow_bt;
    private boolean allow_check;
    private boolean allow_mo;
    private boolean allow_other;
    private boolean allow_paypal;
    private boolean allow_cc;
    private String paypal_email;
    private String name;
    private String first_line;
    private String second_line;
    private String city;
    private String state;
    private String zip;
    private int country_id;
    private int user_id;

    public int getPayment_template_id() {
        return payment_template_id;
    }

    public void setPayment_template_id(int payment_template_id) {
        this.payment_template_id = payment_template_id;
    }

    public boolean isAllow_bt() {
        return allow_bt;
    }

    public void setAllow_bt(boolean allow_bt) {
        this.allow_bt = allow_bt;
    }

    public boolean isAllow_check() {
        return allow_check;
    }

    public void setAllow_check(boolean allow_check) {
        this.allow_check = allow_check;
    }

    public boolean isAllow_mo() {
        return allow_mo;
    }

    public void setAllow_mo(boolean allow_mo) {
        this.allow_mo = allow_mo;
    }

    public boolean isAllow_other() {
        return allow_other;
    }

    public void setAllow_other(boolean allow_other) {
        this.allow_other = allow_other;
    }

    public boolean isAllow_paypal() {
        return allow_paypal;
    }

    public void setAllow_paypal(boolean allow_paypal) {
        this.allow_paypal = allow_paypal;
    }

    public boolean isAllow_cc() {
        return allow_cc;
    }

    public void setAllow_cc(boolean allow_cc) {
        this.allow_cc = allow_cc;
    }

    public String getPaypal_email() {
        return paypal_email;
    }

    public void setPaypal_email(String paypal_email) {
        this.paypal_email = paypal_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_line() {
        return first_line;
    }

    public void setFirst_line(String first_line) {
        this.first_line = first_line;
    }

    public String getSecond_line() {
        return second_line;
    }

    public void setSecond_line(String second_line) {
        this.second_line = second_line;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getListing_payment_id() {
        return listing_payment_id;
    }

    public void setListing_payment_id(int listing_payment_id) {
        this.listing_payment_id = listing_payment_id;
    }

    private int listing_payment_id;
}
