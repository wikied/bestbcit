package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.LocalItem.LocalItem;

public interface QuantityDistributionInterface {

    /*
     * Distributes quantity among offers for a LocalItem.
     */
    LocalItem calculateDistribution(LocalItem item);
}
