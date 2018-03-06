package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields) throws RulesetCollisionException {
        createItemRuleset(localItem, fields);
        createOfferRuleset(localItem, fields);
        return localItem;
    }

    private void createItemRuleset(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException {
        localItem.addField("quantity", fields.get("quantity"));
        localItem.addField("condition", fields.get("condition"));
        localItem.addField("productTitle", fields.get("productTitle"));
        localItem.addField("productDescription", fields.get("productDescription"));
        localItem.addField("productImageUrls", fields.get("productImageUrls"));
    }

    private void createOfferRuleset(LocalItem localItem, Map<String, String> fields)
        throws RulesetCollisionException {
        localItem.addField("sku", fields.get("sku"));
        localItem.addField("categoryId", fields.get("categoryId"));
        localItem.addField("format", fields.get("format"));
        localItem.addField("marketplaceId", fields.get("marketplaceId"));
        localItem.addField("fulfillmentPolicy", fields.get("fulfillmentPolicy"));
        localItem.addField("paymentPolicy", fields.get("fulfillmentPolicy"));
        localItem.addField("returnPolicy", fields.get("fulfillmentPolicy"));
        localItem.addField("currency", fields.get("currency"));
        localItem.addField("value", fields.get("value"));
    }

}
