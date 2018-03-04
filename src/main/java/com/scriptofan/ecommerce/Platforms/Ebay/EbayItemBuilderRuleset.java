package com.scriptofan.ecommerce.Platforms.Ebay;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.ConditionEnum;


import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {


    private String invalidQuantity = "0";

    private final String validFormat = "FIXED_PRICE";

    private boolean validCondition = false;

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
            RulesetViolationException{
        createItemRuleset(localItem, fields);
        createOfferRuleset(localItem, fields);
        return localItem;
    }

    private void createItemRuleset(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException {
        // Quantity //
        if( localItem.getField("quantity").equals(invalidQuantity)
                || localItem.getField("quantity") == null) {
            throw new RulesetViolationException("Invalid quantity");
        } else {
            localItem.addField("quantity", fields.get("quantity"));
        }

        // Condition //
        for (ConditionEnum conditionEnum : ConditionEnum.values()) {
            if(fields.get("condition").equals(conditionEnum.toString())) {
                 validCondition = true;
            }
        }

        if (validCondition == false) {
            throw new RulesetViolationException("Invalid condition");
        } else {
            localItem.addField("condition", fields.get("condition"));
        }

        // Product Title //
        if (fields.get("productTitle") == null) {
            throw new RulesetViolationException("Product title is empty");
        } else {
            localItem.addField("productTitle", fields.get("productTitle"));
        }

        // Product Description //
        if (fields.get("productDescription") == null) {
            throw new RulesetViolationException("Product description is empty");
        } else {
            localItem.addField("productDescription", fields.get("productDescription"));
        }

        // Image Urls //
        if (fields.get("productImageUrls") == null) {
            throw new RulesetViolationException("TmageUrls is empty");
        } else {
            localItem.addField("productImageUrls", fields.get("productImageUrls"));
        }
    }

    private void createOfferRuleset(LocalItem localItem, Map<String, String> fields)
        throws RulesetCollisionException,
               RulesetViolationException {

        if (fields.get("sku") == null) {
            throw new RulesetViolationException("sku is empty");
        } else {
            localItem.addField("sku", fields.get("sku"));
        }

        if (fields.get("categoryId") == null) {
            throw new RulesetViolationException("categoryId is empty");
        } else {
            localItem.addField("categoryId", fields.get("categoryId"));
        }

        if (!(fields.get("format").equals(validFormat))) {
            throw new RulesetViolationException("Invalid format");
        } else {
            localItem.addField("format", fields.get("format"));
        }
        localItem.addField("marketplaceId", fields.get("marketplaceId"));

        if(fields.get("fulfillmentPolicy") == null) {
            throw new RulesetViolationException("fulfillment policy is empty");
        } else {
            localItem.addField("fulfillmentPolicy", fields.get("fulfillmentPolicy"));
        }

        if (fields.get("paymentPolicy") == null) {
            throw new RulesetViolationException("payment policy is empty");
        } else {
            localItem.addField("paymentPolicy", fields.get("paymentPolicy"));
        }

        if(fields.get("returnPolicy") == null) {
            throw new RulesetViolationException("return policy is empty");
        } else {
            localItem.addField("returnPolicy", fields.get("returnPolicy"));
        }
        localItem.addField("currency", fields.get("currency"));

        localItem.addField("value", fields.get("value"));
    }

}
