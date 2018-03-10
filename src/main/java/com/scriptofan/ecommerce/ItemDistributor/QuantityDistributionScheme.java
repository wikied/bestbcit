package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.LocalItem.LocalItem;

public interface QuantityDistributionScheme {

    /*
     * Distributes quantity among offers for a LocalItem.
     */
    LocalItem calculateDistribution(LocalItem item);
}
