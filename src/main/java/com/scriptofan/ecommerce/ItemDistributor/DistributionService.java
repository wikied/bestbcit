package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DistributionService {

    public static final String LOG_DISTRIBUTED = "Item sent to DistributionService";

    @Autowired
    private PlatformRegistry platformRegistry;


    /*
     * Distributes a list of items based on their offers. Returns the complete
     * list of items, with a log of all successes, failures, and issues for each
     * item.
     */
    public List<LocalItem> distribute(List<LocalItem> items) {
        for (LocalItem item : items) {
            distribute(item);
        }
        return items;
    }

    /*
     * Distributes LocalItem based on its offers. Returns the LocalItem, with an
     * updated log of successes, failures and issues.
     */
    public LocalItem distribute(LocalItem item) {
        final Map<String, String> fields = item.getAllFields();
        item.log(LOG_DISTRIBUTED);

        if (platformRegistry == null) {
            throw new NullPointerException("Platform Registry is null");
        }

        QuantityDistributionScheme distributionScheme = platformRegistry.getQuantityDistributionScheme();
        if (distributionScheme == null) {
            throw new NullPointerException("Distribution Scheme is null");
        }

        distributionScheme.calculateDistribution(item);
        for (Offer offer : item.getOffers()) {
            item.log("Posting to " + offer);
            offer.post();
        }

        return item;
    }

}
