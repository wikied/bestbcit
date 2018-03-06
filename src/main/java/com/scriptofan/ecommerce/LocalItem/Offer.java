package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Platforms.Interface.PlatformPublishingService;

public interface Offer {

    /*
     * Returns an instance of the PlatformPublishingService associated with
     * this type of offer (i.e. "EbayPublishingService, EtsyPublishingService,"
     * etc.)
     */
    PlatformPublishingService getPlatformPublishingService();

}
