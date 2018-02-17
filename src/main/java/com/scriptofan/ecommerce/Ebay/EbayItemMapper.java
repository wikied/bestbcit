package com.scriptofan.ecommerce.Ebay;

import com.scriptofan.ecommerce.Ebay.InventoryItem.*;
import com.scriptofan.ecommerce.internal.user.Entity.LocalItem;

/**
 * Provides functionality for mapping local objects to Ebay objects.
 *
 * @author  Patrick Charles-Lundaahl
 * @version 0.1
 */
public class EbayItemMapper {

    public static final ConditionEnum ITEM_DEFAULT_CONDITION = ConditionEnum.NEW;

    /**
     * Creates an Ebay InventoryItem based on the passed LocalItem. Maps
     * LocalItem
     *
     * @param item
     * @return Ebay InventoryItem.
     */
    public InventoryItem createEbayItem(LocalItem item) {
        InventoryItem           ebayItem;
        Product                 product;
        PackageWeightAndSize    packageWeightAndSize;

        // Container Ebay Item, with all the top-level attributes.
        ebayItem = new InventoryItem();
        ebayItem.setSku(item.getSKU());
        ebayItem.setCondition(ITEM_DEFAULT_CONDITION);
        ebayItem.setConditionDescription(null);
        ebayItem.setAvailability(new Availability(item.getQuantity()));

        // Product. Contains all of the descriptive information.
        // TODO: Add aspects
        product = new Product();
        product.setTitle(       item.getTitle());
        product.setDescription( item.getDescription());
        product.setImageUrls(   item.getImages());
        ebayItem.setProduct(product);

        // Dimension Information
        packageWeightAndSize = new PackageWeightAndSize();

        packageWeightAndSize.setDimensions(item.getLength(),
                                           item.getWidth(),
                                           item.getHeight(),
                                           item.getDimensionUnit());

        packageWeightAndSize.setWeight(item.getWeightUnit(),
                                       item.getWeight());
        ebayItem.setPackageWeightAndSize(packageWeightAndSize);

        return ebayItem;
    }
}
