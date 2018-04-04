package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Etsy.Exception.EtsyCreateListingException;
import com.scriptofan.ecommerce.Platforms.Etsy.Services.EtsyListingService;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.Platforms.Interface.OfferState;

import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

public class EtsyLocalOffer extends LocalOffer {

    private EtsyListingService etsyListingService;
    public EtsyLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    public CompletableFuture<LocalOffer> post() {

        etsyListingService = new EtsyListingService();
        try {
            etsyListingService.creatingListing(this);
            setState(OfferState.POST_SUCCESS);
        }
        catch (RulesetViolationException e) {
            setState(OfferState.POST_FAILED);
            this.log(e.getMessage());
        }
        catch (EtsyCreateListingException e) {
            setState(OfferState.POST_FAILED);
            this.log("Etsy createListingException failed:" + e.getMessage());
        }
        catch (MalformedURLException e) {
            setState(OfferState.POST_FAILED);
            this.log("Malformed URL: " + e.getMessage());
        }

        return CompletableFuture.completedFuture(this);
    }

    public String toString() {
        return "EtsyLocalOffer";
    }
}
