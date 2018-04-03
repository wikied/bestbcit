package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.Exception.*;
import com.scriptofan.ecommerce.Platforms.Ebay.Services.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.EbayPublishOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Entity.Offer.EbayRemoteOffer;
import com.scriptofan.ecommerce.Platforms.Ebay.Services.OfferService;
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
        } catch (EbayPublishOfferException e) {
            e.printStackTrace();
        } catch (EbayCreateOfferException e) {
            e.printStackTrace();
        } catch (EbayCreateInventoryItemException e) {
            e.printStackTrace();
        } catch (OfferAlreadyExistsException e) {
            e.printStackTrace();
        } catch (BadEbayTokenException e) {
            e.printStackTrace();
        } catch (Ebay500ServerException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(this);
    }
}
