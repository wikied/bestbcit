package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.ItemDistributor.QuantityDistributionScheme;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;

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
        int numberOfOffers = item.getOffers().size();

        // Handle zero offers
        if (numberOfOffers > 0) {

            quantity = item.getTotalQuantity();
            quantityPerOffer = quantity / numberOfOffers;

            for (Offer offer : item.getOffers()) {

                if (quantity > quantityPerOffer * 2) {
                    quantity -= quantityPerOffer;
                    offer.setQuantity(quantityPerOffer);
                } else {
                    offer.setQuantity(quantity);
                }
            }
        }
        return item;
    }
}
