package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.internal.user.Entity.GenericOffer;

public class EbayOffer extends GenericOffer {

    private String sku;
    private String marketplaceId;
    private String format;
    private String categoryId;
    private String merchantLocationKey;

    private String paymentPolicyId;
    private String returnPolicyId;
    private String fulfillmentPolicyId;

    private String currency;
    private String value;

    /**
     * Default Constructor
     */
    public EbayOffer(String platformAccountId) {
        super(platformAccountId);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }

    public String getPaymentPolicyId() {
        return paymentPolicyId;
    }

    public void setPaymentPolicyId(String paymentPolicyId) {
        this.paymentPolicyId = paymentPolicyId;
    }

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getFulfillmentPolicyId() {
        return fulfillmentPolicyId;
    }

    public void setFulfillmentPolicyId(String fulfillemtPolicyId) {
        this.fulfillmentPolicyId = fulfillemtPolicyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
