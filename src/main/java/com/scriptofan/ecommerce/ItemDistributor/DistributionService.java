package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformPublishingService;

import java.util.List;
import java.util.Map;

public class DistributionService {

    /*
     * Distributes a list of items based on their offers. Returns the complete
     * list of items, with a log of all successes, failures, and issues for each
     * item.
     */
    public List<LocalItem> distribute(List<LocalItem> items) throws NotImplementedException {
        for (LocalItem item : items) {
            distribute(item);
        }
        return items;
    }

    /*
     * Distributes LocalItem based on its offers. Returns the LocalItem, with an
     * updated log of successes, failures and issues.
     */
    public LocalItem distribute(LocalItem item) throws NotImplementedException {
        final Map<String, String> fields = item.getAllFields();

        for (Offer offer : item.getOffers()) {
            PlatformPublishingService publisher = offer.getPlatformPublishingService();
            publisher.publish(fields, offer);
        }

        return item;
    }

}
