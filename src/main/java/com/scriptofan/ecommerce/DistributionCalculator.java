package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.ItemDistributor.QuantityDistributionScheme;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;

public class DistributionCalculator
        implements QuantityDistributionScheme
{
    /*
     * Distributes quantity among offers for a LocalItem.
     */
    public LocalItem calculateDistribution(LocalItem item)
    {
        int quantity;
        int quantityPerOffer;
        int numberOfOffers = item.getLocalOffers().size();

        // Handle zero offers
        if (numberOfOffers > 0) {

            quantity = item.getTotalQuantity();
            quantityPerOffer = quantity / numberOfOffers;

            for (LocalOffer localOffer : item.getLocalOffers()) {

                if (quantity > quantityPerOffer * 2) {
                    quantity -= quantityPerOffer;
                    localOffer.setQuantity(quantityPerOffer);
                } else {
                    localOffer.setQuantity(quantity);
                }
            }
        }
        return item;
    }
}
