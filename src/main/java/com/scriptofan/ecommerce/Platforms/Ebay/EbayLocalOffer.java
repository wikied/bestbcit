package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;

public class EbayLocalOffer extends Offer{

    public EbayLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {
        EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(getLocalItem().getUser().getUserToken(),
                                                           getLocalItem().getField("sku"),
                                                          this);

    }



}
