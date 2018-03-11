package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.ConditionEnum;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.CurrencyCode;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.MarketplaceEnum;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {

    private final String validFormat = "FIXED_PRICE";

    private boolean validCondition = false;

    private boolean validMarketPlaceId = false;

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
            RulesetViolationException {

        buildItem(localItem, fields);
        buildOffer(localItem, fields);
        return localItem;
    }

    private void buildItem(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException
    {
        if (fields == null) {
            throw new NullPointerException("Fields is null");
        }

        String condition = fields.get("condition");
        if (condition == null) {
            throw new RulesetViolationException("condition cannot be null");
        }
        for (ConditionEnum conditionEnum : ConditionEnum.values()) {
            if(condition.equals(conditionEnum.toString())) {
                 validCondition = true;
                 break;
            }
        }

        if (!validCondition) {
            throw new RulesetViolationException("Invalid condition");
        } else {
            localItem.addField("condition", fields.get("condition").toUpperCase());
        }

        // Product Title //
        if (fields.get("productTitle") == null) {
            throw new RulesetViolationException("productTitle is empty");
        } else {
            localItem.addField("productTitle", fields.get("productTitle"));
        }

        // Product Description //
        if (fields.get("productDescription") == null) {
            throw new RulesetViolationException("productDescription is empty");
        } else {
            localItem.addField("productDescription", fields.get("productDescription"));
        }

        // Image Urls //
        if (fields.get("productImageUrls") == null) {
            throw new RulesetViolationException("productImageUrls is empty");
        } else {
            localItem.addField("productImageUrls", fields.get("productImageUrls"));
        }
    }

    private void buildOffer(LocalItem localItem, Map<String, String> fields)
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
            throw new RulesetViolationException("fulfillmentPolicy is empty");
        } else {
            localItem.addField("fulfillmentPolicy", fields.get("fulfillmentPolicy"));
        }

        // Payment Policy
        if (fields.get("paymentPolicy") == null) {
            throw new RulesetViolationException("paymentPolicy is empty");
        } else {
            localItem.addField("paymentPolicy", fields.get("paymentPolicy"));
        }

        // Return Policy
        if (fields.get("returnPolicy") == null) {
            throw new RulesetViolationException("returnPolicy is empty");
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
            throw new RulesetViolationException("value (price) is empty");
        } else {
            localItem.addField("value", fields.get("value"));
        }
    }

}
