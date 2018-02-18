package com.scriptofan.ecommerce.internal.user;

import com.scriptofan.ecommerce.Ebay.EbayOffer;
import com.scriptofan.ecommerce.Ebay.Offer.Amount;
import com.scriptofan.ecommerce.Ebay.Offer.ListingPolicies;
import com.scriptofan.ecommerce.Ebay.Offer.PricingSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ebay-json-test/offer")
public class EbayOfferTestController {

    @GetMapping
    public EbayOffer getTestEbayOffer() {
        EbayOffer offer = new EbayOffer("123444");

        ListingPolicies listingPolicies = new ListingPolicies();
        listingPolicies.setFulfillmentPolicyId("1232131");
        listingPolicies.setReturnPolicyId("12313141");
        listingPolicies.setPaymentPolicyId("63421");

        Amount price = new Amount();
        price.setCurrency("USD");
        price.setValue("100.00");
        PricingSummary pricingSummary = new PricingSummary();
        pricingSummary.setPrice(price);

        offer.setCategoryId("30123");
        offer.setMarketplaceId("EBAY_US");
        offer.setFormat("FIXED_PRICE");
        offer.setMerchantLocationKey("1234");
        offer.setSku("1234");
        offer.setListingPolicies(listingPolicies);
        offer.setPricingSummary(pricingSummary);
        return offer;

    }

}
