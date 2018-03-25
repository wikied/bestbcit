package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Handles distributing LocalItems among the available retail platforms.
 * Specifically, each LocalItem will have 0 or more attached Offers,
 * each associated with a retail platform (1). This service divides the
 * quantity among the attached offers (2) and calls post() on each offer.
 *
 * (1) Retail Platforms are fetched from the PlatformRegistry. The best
 * place to add them to the registry is in Config.
 *
 * (2) The particular scheme for distributing available quantity among
 * offers is dictated by the QuantityDistributionScheme.
 */
@Service
public class DistributionService {

    /* This message is added to LocalItems when they are run through this service */
    public static final String LOG_DISTRIBUTED = "Item sent to DistributionService";

    @Autowired
    private PlatformRegistry platformRegistry;

    /*
     * Distributes a list of items based on their offers. Returns the complete
     * list of items, with a log of all successes, failures, and issues for each
     * item.
     */
    public List<LocalItem> distribute(List<LocalItem> items) {

        int nItems = items.size();
        CompletableFuture<LocalItem>[] itemFutures = new CompletableFuture[nItems];

        for (int i=0; i < nItems; ++i) {
            CompletableFuture<LocalItem> itemPostFuture;
            itemPostFuture = this.distribute(items.get(i));
            itemFutures[i] = itemPostFuture;
        }

        CompletableFuture.allOf(itemFutures).join();
        return items;
    }

    /*
     * Distributes LocalItem based on its offers. Returns the LocalItem, with an
     * updated log of successes, failures and issues.
     */
    @Async
    public CompletableFuture<LocalItem> distribute(LocalItem item) {
        final Map<String, String> fields = item.getAllFields();
        item.log(LOG_DISTRIBUTED);

        try {
            if (platformRegistry == null) {
                throw new NullPointerException("Platform Registry is null");
            }

            QuantityDistributionScheme distributionScheme = platformRegistry.getQuantityDistributionScheme();
            if (distributionScheme == null) {
                throw new NullPointerException("Distribution Scheme is null");
            }

            distributionScheme.calculateDistribution(item);
            for (LocalOffer localOffer : item.getLocalOffers()) {
                item.log("Posting to " + localOffer);
                localOffer.post();
            }
        }
        catch (NullPointerException e) {
            String output = "NullPointerException: " + e.getMessage() + "\n";
            for (StackTraceElement element : e.getStackTrace()) {
                output += element.toString() + "\n";
            }
            item.log(output);
        }

        return CompletableFuture.completedFuture(item);
    }

}
