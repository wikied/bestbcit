package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.Ebay.InventoryItem.*;
import com.scriptofan.ecommerce.internal.user.Entity.LocalItem;

/**
 * Provides functionality for mapping local objects to Ebay objects.
 *
 * @author  Patrick Charles-Lundaahl
 * @version 0.1
 */
public class EbayMapper {

    public static final ConditionEnum ITEM_DEFAULT_CONDITION = ConditionEnum.NEW;

    /**
     * Creates an Ebay InventoryItem based on the passed LocalItem. Maps
     * LocalItem
     *
     * @param item
     * @return Ebay InventoryItem.
     */
    public InventoryItem createEbayItem(LocalItem item) {
        InventoryItem           ebayItem = null;

        Availability            availability;
        PackageWeightAndSize    packageWeightAndSize;
        Product                 product;

        // Product. Contains specific product details.
        product = new Product();
        product.setTitle(       item.getTitle());
        product.setDescription( item.getDescription());
        product.setImageUrls(   item.getImages());
        // TODO: Aspects

        availability = new Availability(item.getQuantity());

        packageWeightAndSize = new PackageWeightAndSize();


        ebayItem.setSku(item.getSKU());
        ebayItem.setCondition(ITEM_DEFAULT_CONDITION);
        ebayItem.setConditionDescription(null);
        ebayItem.setAvailability(availability);

        return ebayItem;
    }
}
