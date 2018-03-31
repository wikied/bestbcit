package com.scriptofan.ecommerce.ItemDistributor;

import com.scriptofan.ecommerce.LocalItem.LocalItem;

/**
 * Encapsulates a strategy for distributing a LocalItem's quantity
 * among all of its attached offers.
 */
public interface QuantityDistributionScheme {

    /*
     * Distributes a LocalItem's quantity among all of its
     * attached offers.
     */
    LocalItem calculateDistribution(LocalItem item);
}
