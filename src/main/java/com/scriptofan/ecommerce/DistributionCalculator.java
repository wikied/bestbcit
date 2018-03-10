package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.ItemDistributor.QuantityDistributionInterface;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;

public class DistributionCalculator
        implements QuantityDistributionInterface
{
    /*
     * Distributes quantity among offers for a LocalItem.
     */
    public LocalItem calculateDistribution(LocalItem item)
    {
        int quantity;
        int quantityPerOffer;

        quantity         = item.getTotalQuantity();
        quantityPerOffer = quantity / item.getOffers().size();

        for (Offer offer : item.getOffers()) {

            if (quantity > quantityPerOffer * 2) {
                quantity -= quantityPerOffer;
                offer.setQuantity(quantityPerOffer);
            } else {
                offer.setQuantity(quantity);
            }
        }
        return item;
    }
}
