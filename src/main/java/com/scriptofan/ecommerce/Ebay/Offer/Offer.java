package com.scriptofan.ecommerce.Ebay.Offer;

//
public class Offer {

    // The unique sku used for the item in the sellers inventory
    String sku;

    // The id of the eBay marketplace that the offer will be placed in
    // All valid values are listed in the marketplaceEnum
    //https://developer.ebay.com/api-docs/sell/inventory/types/slr:MarketplaceEnum
    String marketplaceId;

    // The listing format for the offer.
    // Currently only FIXED_PRICE is the only supported value for the Inventory API
    // Other values are in the FormatTypeEnum
    // https://developer.ebay.com/api-docs/sell/inventory/types/slr:FormatTypeEnum
    final String FORMAT = "FIXED_PRICE";

    // The unique identifier of the eBay category of the listing item
    String categoryId;

    // Contains information of the listing policies of the offer
    ListingPolicies listingPolicies;

    // The unique identifier of a merchants inventory location
    String merchantLocationKey;

    // Constructor
    public Offer() {
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

    public String getFORMAT() {
        return FORMAT;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ListingPolicies getListingPolicies() {
        return listingPolicies;
    }

    public void setListingPolicies(ListingPolicies listingPolicies) {
        this.listingPolicies = listingPolicies;
    }

    public String getMerchantLocationKey() {
        return merchantLocationKey;
    }

    public void setMerchantLocationKey(String merchantLocationKey) {
        this.merchantLocationKey = merchantLocationKey;
    }
}
