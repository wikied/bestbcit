package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.CurrencyCode;
import java.util.Map;

public class EtsyListingBuilderRuleset implements ItemBuilderRuleset {

    private boolean validWhoMade = false;

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException {
        buildListing(localItem, fields);
        return localItem;
    }

    private void buildListing(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException {

        if (fields == null) {
            throw new NullPointerException("Fields is null");
        }

        // Title
        if (fields.get("title") == null) {
            throw new RulesetViolationException("Title is empty");
        } else {
            localItem.addField("title", fields.get("title"));
        }

        // Description
        if (fields.get("description") == null) {
            throw new RulesetViolationException("Description is empty");
        } else {
            localItem.addField("description", fields.get("description"));
        }

        // Price
        if (fields.get("price") == null) {
            throw new RulesetViolationException("Price is empty");
        } else {
            localItem.addField("price", fields.get("price"));
        }

        // Tags
        if (fields.get("tags") == null) {
            throw new RulesetViolationException("Tags is empty");
        } else {
            localItem.addField("tags", fields.get("tags"));
        }

        // Shipping template id
        if (fields.get("shippingTemplateId") == null) {
            throw new RulesetViolationException("shipping_template id");
        } else {
            localItem.addField("shippingTemplateId", fields.get("shippingTemplateId"));
        }

        // Who made
        for (WhoMadeEnum whoMadeEnum : WhoMadeEnum.values()) {
            if(fields.get("whoMade").equals(whoMadeEnum.toString())) {
                validWhoMade = true;
                break;
            }
        }

        if (!validWhoMade) {
            throw new RulesetViolationException("invalid who_made");
        } else {
            localItem.addField("whoMade", fields.get("whoMade"));
        }

        // Currency Code
        if (!CurrencyCode.currencies.contains(fields.get("currencyCode"))) {
            throw new RulesetViolationException("Invalid currency code");
        } else {
            localItem.addField("currencyCode", fields.get("currencyCode"));
        }


    }
}
