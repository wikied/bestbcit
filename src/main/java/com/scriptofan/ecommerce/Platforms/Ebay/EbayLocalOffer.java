package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.*;
import com.scriptofan.ecommerce.Platforms.Ebay.Services.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.EbayPublishOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.EbayRemoteOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Services.OfferService;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.Platforms.Interface.OfferState;
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
        String          offerId;
        String          ebayOAuthToken;
        String          itemSku;
        EbayRemoteOffer ebayRemoteOffer;

        offerService    = new OfferService();
        ebayOAuthToken  = getLocalItem().getUser().getUserToken();
        itemSku         = getLocalItem().getField("sku");

        try {
            System.err.println(TAG + " ~ CREATE OR REPLACE INVENTORY ITEM");
            EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(
                    ebayOAuthToken,
                    itemSku,
                    this);

            System.err.println(TAG + " ~ CREATE REMOTE OFFER");
            ebayRemoteOffer = offerService.buildEbayOffer(this);
            offerId         = offerService.createOffer(ebayRemoteOffer, ebayOAuthToken);
            System.err.println("Offer ID: " + offerId);

            System.err.println(TAG + " ~ PUBLISH REMOTE OFFER");
            EbayPublishOffer.publishEbayOffer(offerId, ebayOAuthToken);
        }
        catch (EbayCreateInventoryItemException e) {
            this.setState(OfferState.POST_FAILED);
            this.log("createInventoryItem failed: " + e.getMessage());
        }
        catch (EbayCreateOfferException e) {
            this.setState(OfferState.POST_FAILED);
            this.log("createOffer failed: " + e.getMessage());
        }
        catch (EbayPublishOfferException e) {
            this.setState(OfferState.POST_FAILED);
            this.log("publishOffer failed: " + e.getMessage());
        }
        catch (OfferAlreadyExistsException e) {
            System.err.println("OFFER ALREADY EXISTS");
        }
        catch (BadEbayTokenException e) {
            this.setState(OfferState.POST_FAILED);
            this.log("Failed: eBay OAuth token is invalid");
        }
        catch (Ebay500ServerException e) {
            this.setState(OfferState.POST_FAILED);
            this.log("Failed: eBay had a server error.");
        }

        for (String log : this.getFullLog()) {
            System.err.println(log);
        }

        return CompletableFuture.completedFuture(this);
    }
}
