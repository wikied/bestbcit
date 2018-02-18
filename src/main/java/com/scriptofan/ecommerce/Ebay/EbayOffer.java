package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.internal.user.Entity.GenericOffer;
import com.scriptofan.ecommerce.Ebay.Offer.*;

public class EbayOffer extends GenericOffer {

    private String sku;
    private String marketplaceId;
    private String format;
    private String categoryId;
    private String merchantLocationKey;

    private ListingPolicies listingPolicies;
    private PricingSummary pricingSummary;


    /**
     * Default Constructor
     */
    public EbayOffer(String platformAccountId) {
        super(platformAccountId);
    }

    /**
     * Getters and Setters
     */

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

    public ListingPolicies getListingPolicies() {
        return listingPolicies;
    }

    public void setListingPolicies(ListingPolicies listingPolicies) {
        this.listingPolicies = listingPolicies;
    }

    public PricingSummary getPricingSummary() {
        return pricingSummary;
    }

    public void setPricingSummary(PricingSummary pricingSummary) {
        this.pricingSummary = pricingSummary;
    }
}
