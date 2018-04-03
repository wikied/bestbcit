package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

public class EtsyLocalOffer extends LocalOffer {

    private EtsyListingService etsyListingService;
    public EtsyLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    public CompletableFuture<LocalOffer> post() throws MalformedURLException, UnsupportedEncodingException {

        etsyListingService = new EtsyListingService();
        try {
            etsyListingService.creatingListing(this);
        } catch (RulesetViolationException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(this);
    }
}
