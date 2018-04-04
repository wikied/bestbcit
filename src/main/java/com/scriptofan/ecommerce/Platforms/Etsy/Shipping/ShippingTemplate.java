package com.scriptofan.ecommerce.Platforms.Etsy.Shipping;

public class ShippingTemplate {
    private int shipping_template_id;
    private String title;
    private int user_id;
    private int min_processing_days;
    private int max_processing_days;
    private String processing_days_display_label;
    private int origin_country_id;

    public int getShipping_template_id() {
        return shipping_template_id;
    }

    public void setShipping_template_id(int shipping_template_id) {
        this.shipping_template_id = shipping_template_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMin_processing_days() {
        return min_processing_days;
    }

    public void setMin_processing_days(int min_processing_days) {
        this.min_processing_days = min_processing_days;
    }

    public int getMax_processing_days() {
        return max_processing_days;
    }

    public void setMax_processing_days(int max_processing_days) {
        this.max_processing_days = max_processing_days;
    }

    public String getProcessing_days_display_label() {
        return processing_days_display_label;
    }

    public void setProcessing_days_display_label(String processing_days_display_label) {
        this.processing_days_display_label = processing_days_display_label;
    }

    public int getOrigin_country_id() {
        return origin_country_id;
    }

    public void setOrigin_country_id(int origin_country_id) {
        this.origin_country_id = origin_country_id;
    }
}
