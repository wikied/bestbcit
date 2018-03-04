package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class DistributionService {

    /*
     * Distributes a list of items based on their offers. Returns the complete
     * list of items, with a log of all successes, failures, and issues for each
     * item.
     */
    public List<LocalItem> distribute(List<LocalItem> items) {
        throw new NotImplementedException();
    }

    /*
     * Distributes LocalItem based on its offers. Returns the LocalItem, with an
     * updated log of successes, failures and issues.
     */
    public LocalItem distribute(LocalItem item) {
        throw new NotImplementedException();
    }

    // Distribute should create call getPlatformPublishingService for each Offer
    // Distribute should call publish() for each returned platformPublishingService.
    // Distribute should feed all of the current LocalItem's fields into publish()
    // Distribute should feed only the current offer into publish()

}
