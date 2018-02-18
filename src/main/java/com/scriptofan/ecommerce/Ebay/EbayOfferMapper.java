package com.scriptofan.ecommerce.Ebay;
import com.scriptofan.ecommerce.Ebay.Offer.*;


/**
 * Provides functionality for mapping local objects to Ebay objects.
 *
 * @author  Daniel Zhang
 * @version 0.1
 */
public class EbayOfferMapper {

    /**
     * Creates an Ebay Offer based on the passed EbayOffer. Maps
     * EbayOffer
     *
     * @param offer
     * @return Ebay Offer.
     */
    public Offer createEbayOffer(EbayOffer offer) {

        Offer ebayOffer;

        ebayOffer = new Offer();
        ebayOffer.setSku(offer.getSku());
        ebayOffer.setCategoryId(offer.getCategoryId());
        ebayOffer.setMarketplaceId(offer.getMarketplaceId());
        ebayOffer.setMerchantLocationKey(offer.getMerchantLocationKey());
        ebayOffer.setListingPolicies(offer.getListingPolicies());
        ebayOffer.setPricingSummary(offer.getPricingSummary());

        return ebayOffer;
    }
}
