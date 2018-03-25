package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.EbayPublishOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class EbayLocalOffer extends LocalOffer {

    private OfferService offerService;

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    @Async
    public CompletableFuture<LocalOffer> post() {
        String  token;
        Offer   offer;
        String offerId;

        offerService = new OfferService();
        token = getLocalItem().getUser().getUserToken();

        System.err.println("creating inventory item");
        EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(token,
                                                           getLocalItem().getField("sku"),
                                                          this);

        offer = offerService.offerBuilder(this);
        System.err.println(offer);
        System.err.println("Creating offer");
        offerId = offerService.createOffer(offer,token);
        System.err.println("publishing offer");
        EbayPublishOffer.publishEbayOffer(offerId, token);

        return CompletableFuture.completedFuture(this);
    }
}
