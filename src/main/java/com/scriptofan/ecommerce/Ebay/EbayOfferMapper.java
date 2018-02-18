package com.scriptofan.ecommerce.Ebay;
import com.scriptofan.ecommerce.Ebay.Offer.*;
import com.scriptofan.ecommerce.internal.user.Entity.LocalOffer;

/**
 * Provides functionality for mapping local objects to Ebay objects.
 *
 * @author  Daniel Zhang
 * @version 0.1
 */
public class EbayOfferMapper {

    /**
     * Creates an Ebay Offer based on the passed LocalOffer. Maps
     * LocalOffer
     *
     * @param offer
     * @return Ebay Offer.
     */
    public Offer createEbayOffer(LocalOffer offer) {

        Offer ebayOffer;
        Amount amount;
        ListingPolicies policies;
        PricingSummary summary;

        ebayOffer = new Offer();
        ebayOffer.setSku(offer.getSku());
        ebayOffer.setCategoryId(offer.getCategoryId());
        ebayOffer.setMarketplaceId(offer.getMarketplaceId());
        ebayOffer.setMerchantLocationKey(offer.getMerchantLocationKey());


        // Amount information
        amount = new Amount();
        amount.setCurrency(offer.getCurrency());
        amount.setValue(offer.getValue());

        // Policy Information
        policies = new ListingPolicies();
        policies.setPaymentPolicyId(offer.getPaymentPolicyId());
        policies.setReturnPolicyId(offer.getReturnPolicyId());
        policies.setFulfillmentPolicyId(offer.getFulfillmentPolicyId());

        // Pricing Summary information
        summary = new PricingSummary();
        summary.setPrice(amount);


        return ebayOffer;
    }
}
