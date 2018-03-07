package com.scriptofan.ecommerce.Platforms.Ebay;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.ConditionEnum;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.CurrencyCode;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.MarketplaceEnum;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {

    private final String validFormat = "FIXED_PRICE";

    private boolean validCondition = false;

    private boolean validMarketPlaceId = false;

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
            RulesetViolationException {

        applyItemRuleset(localItem, fields);
        applyOfferRuleset(localItem, fields);
        return localItem;
    }

    private void applyItemRuleset(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException {

        // Condition //
        for (ConditionEnum conditionEnum : ConditionEnum.values()) {
            if(fields.get("condition").equals(conditionEnum.toString())) {
                 validCondition = true;
                 break;
            }
        }

        if (!validCondition) {
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

    private void applyOfferRuleset(LocalItem localItem, Map<String, String> fields)
        throws RulesetCollisionException,
               RulesetViolationException {

        // sku
        if (fields.get("sku") == null) {
            throw new RulesetViolationException("sku is empty");
        } else {
            localItem.addField("sku", fields.get("sku"));
        }

        // category id
        if (fields.get("categoryId") == null) {
            throw new RulesetViolationException("categoryId is empty");
        } else {
            localItem.addField("categoryId", fields.get("categoryId"));
        }

        // format
        if (!(fields.get("format").equals(validFormat))) {
            throw new RulesetViolationException("Invalid format");
        } else {
            localItem.addField("format", fields.get("format"));
        }

        // Marketplace Id
        for (MarketplaceEnum marketplaceEnum : MarketplaceEnum.values()) {
            if(fields.get("marketplaceId").equals(marketplaceEnum.toString())) {
                validMarketPlaceId = true;
                break;
            }
        }

        if (!validMarketPlaceId) {
            throw new RulesetViolationException("Invalid condition");
        } else {
            localItem.addField("marketplaceId", fields.get("marketplaceId"));
        }

        // Fulfillment Policy
        if(fields.get("fulfillmentPolicy") == null) {
            throw new RulesetViolationException("fulfillment policy is empty");
        } else {
            localItem.addField("fulfillmentPolicy", fields.get("fulfillmentPolicy"));
        }

        // Payment Policy
        if (fields.get("paymentPolicy") == null) {
            throw new RulesetViolationException("payment policy is empty");
        } else {
            localItem.addField("paymentPolicy", fields.get("paymentPolicy"));
        }

        // Return Policy
        if (fields.get("returnPolicy") == null) {
            throw new RulesetViolationException("return policy is empty");
        } else {
            localItem.addField("returnPolicy", fields.get("returnPolicy"));
        }

        // Currency Id
        if (!CurrencyCode.currencies.contains(fields.get("currencyCode"))) {
            throw new RulesetViolationException("Invalid currency code");
        } else {
            localItem.addField("currencyCode", fields.get("currencyCode"));
        }

        // Value
        if (fields.get("value") == null) {
            throw new RulesetViolationException("value is empty");
        } else {
            localItem.addField("value", fields.get("value"));
        }
    }

}
