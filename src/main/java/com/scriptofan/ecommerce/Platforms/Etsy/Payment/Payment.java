package com.scriptofan.ecommerce.Platforms.Etsy.Payment;

public class Payment {
    private int payment_id;
    private int buyer_user_id;
    private int shop_id;
    private int receipt_id;
    private int amount_gross;
    private int amount_fees;
    private int amount_net;
    private int posted_gross;
    private int posted_fees;
    private int posted_net;
    private int adjusted_gross;
    private int adjusted_fees;
    private String currency;
    private String shop_currency;
    private String buyer_currency;
    private int shipping_user_id;
    private int shipping_address_id;
    private int billing_address_id;
    private String status;
    private int shipped_date;
    private int create_date;
    private int update_date;

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getBuyer_user_id() {
        return buyer_user_id;
    }

    public void setBuyer_user_id(int buyer_user_id) {
        this.buyer_user_id = buyer_user_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getReceipt_id() {
        return receipt_id;
    }

    public void setReceipt_id(int receipt_id) {
        this.receipt_id = receipt_id;
    }

    public int getAmount_gross() {
        return amount_gross;
    }

    public void setAmount_gross(int amount_gross) {
        this.amount_gross = amount_gross;
    }

    public int getAmount_fees() {
        return amount_fees;
    }

    public void setAmount_fees(int amount_fees) {
        this.amount_fees = amount_fees;
    }

    public int getAmount_net() {
        return amount_net;
    }

    public void setAmount_net(int amount_net) {
        this.amount_net = amount_net;
    }

    public int getPosted_gross() {
        return posted_gross;
    }

    public void setPosted_gross(int posted_gross) {
        this.posted_gross = posted_gross;
    }

    public int getPosted_fees() {
        return posted_fees;
    }

    public void setPosted_fees(int posted_fees) {
        this.posted_fees = posted_fees;
    }

    public int getPosted_net() {
        return posted_net;
    }

    public void setPosted_net(int posted_net) {
        this.posted_net = posted_net;
    }

    public int getAdjusted_gross() {
        return adjusted_gross;
    }

    public void setAdjusted_gross(int adjusted_gross) {
        this.adjusted_gross = adjusted_gross;
    }

    public int getAdjusted_fees() {
        return adjusted_fees;
    }

    public void setAdjusted_fees(int adjusted_fees) {
        this.adjusted_fees = adjusted_fees;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getShop_currency() {
        return shop_currency;
    }

    public void setShop_currency(String shop_currency) {
        this.shop_currency = shop_currency;
    }

    public String getBuyer_currency() {
        return buyer_currency;
    }

    public void setBuyer_currency(String buyer_currency) {
        this.buyer_currency = buyer_currency;
    }

    public int getShipping_user_id() {
        return shipping_user_id;
    }

    public void setShipping_user_id(int shipping_user_id) {
        this.shipping_user_id = shipping_user_id;
    }

    public int getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(int shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    public int getBilling_address_id() {
        return billing_address_id;
    }

    public void setBilling_address_id(int billing_address_id) {
        this.billing_address_id = billing_address_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(int shipped_date) {
        this.shipped_date = shipped_date;
    }

    public int getCreate_date() {
        return create_date;
    }

    public void setCreate_date(int create_date) {
        this.create_date = create_date;
    }

    public int getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(int update_date) {
        this.update_date = update_date;
    }
}
