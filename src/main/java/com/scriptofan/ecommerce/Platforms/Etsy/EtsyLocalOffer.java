package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

import java.util.concurrent.CompletableFuture;

public class EtsyLocalOffer extends LocalOffer {

    private EtsyListingService etsyListingService;
    public EtsyLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    public CompletableFuture<LocalOffer> post() {

        String etsyOauthToken = "3c1d64df55ecc102368d33c809eb8b";

        etsyListingService = new EtsyListingService();
        etsyListingService.creatingListing(this, etsyOauthToken);

        return CompletableFuture.completedFuture(this);
    }
}
