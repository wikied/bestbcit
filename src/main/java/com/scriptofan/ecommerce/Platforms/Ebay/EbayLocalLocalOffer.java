package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.EbayCreateOrReplaceItemService;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

public class EbayLocalLocalOffer extends LocalOffer {

//    @Autowired
//    private OfferService offerService;

    public EbayLocalLocalOffer(LocalItem localItem) {
        super(localItem);
    }

    public void post() {

        EbayCreateOrReplaceItemService.createOrReplaceInventoryItem(getLocalItem().getUser().getUserToken(),
                                                           getLocalItem().getField("sku"),
                                                          this);

    }



}
