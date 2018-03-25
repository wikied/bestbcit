package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.EbayPublishOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.EbayRemoteOffer;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class EbayLocalOffer extends LocalOffer {

    public static final String TAG = EbayLocalOffer.class.getName();
    private OfferService offerService;

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    @Async
    public CompletableFuture<LocalOffer> post() {
        EbayRemoteOffer ebayRemoteOffer;

        String  offerId;
        String  ebayOAuthToken;
        String  itemSku;

        offerService    = new OfferService();
        ebayOAuthToken  = getLocalItem().getUser().getUserToken();
        itemSku         = getLocalItem().getField("sku");

        try {
            System.err.println("\n" + TAG + " ~ Creating Inventory Item");
            EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(
                    ebayOAuthToken,
                    itemSku,
                    this);

            System.err.println("\n" + TAG + " ~ Creating EbayRemoteOffer");
            ebayRemoteOffer = offerService.offerBuilder(this);
            System.err.println(ebayRemoteOffer);
            offerId = offerService.createOffer(ebayRemoteOffer, ebayOAuthToken);

            System.err.println("\n" + TAG + " ~ Publishing EbayRemoteOffer");
            EbayPublishOffer.publishEbayOffer(offerId, ebayOAuthToken);
        }
        catch (NullPointerException e) {
            // Do stuff
        }

        return CompletableFuture.completedFuture(this);
    }
}
