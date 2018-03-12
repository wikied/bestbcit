package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

public class EbayLocalOffer extends LocalOffer {

    private OfferService offerService;

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {
        String  token;
        Offer   offer;

        offerService = new OfferService();

        token = getLocalItem().getUser().getUserToken();

        EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(token,
                                                           getLocalItem().getField("sku"),
                                                          this);

        offer = offerService.offerBuilder(this);
        System.err.println(offer);
        offerService.createOffer(offer,token);
    }



}
