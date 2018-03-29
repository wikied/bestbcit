package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

import java.util.concurrent.CompletableFuture;

public class EtsyLocalOffer extends LocalOffer {

    public EtsyLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    @Override
    public CompletableFuture<LocalOffer> post() {
        

        return CompletableFuture.completedFuture(this);
    }
}
