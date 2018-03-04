package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.Offer;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

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
        final Map<String, String> fields = item.getAllFields();

        for (Offer offer : item.getOffers()) {
            PlatformPublishingService publisher = offer.getPlatformPublishingService();
            publisher.publish(fields, offer);
        }

        return item;
    }

}
