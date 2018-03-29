package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.CurrencyCode;
import java.util.Map;

/**
 * This class builds the local item for Etsy by going through the fields
 * of the csv and adds the required fields to the local item
 **/
public class EtsyListingBuilderRuleset implements ItemBuilderRuleset {

    private boolean validWhoMade = false;

    private String isSupplyTrue = "true";

    private String isSupplyFalse = "false";

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
        if (fields.get("value") == null) {
            throw new RulesetViolationException("Price is empty");
        } else {
            localItem.addField("value", fields.get("value"));
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

        // When Made
        if (!WhenMade.whenMade.contains(fields.get("whenMade"))) {
            throw new RulesetViolationException("Invalid whenMade");
        } else {
            localItem.addField("whenMade", fields.get("whenMade"));
        }

        // State
        // Forces the listing to be in the draft state - change this later!!
        if (Config.forceEtsyDraft) {
            localItem.addField("state", "draft");
        }

        // Is supply
        if (fields.get("isSupply") == null) {
            throw new RulesetViolationException("isSupply is empty");
        } else {
            if (fields.get("isSupply").equals(isSupplyTrue) || fields.get("isSupply").equals(isSupplyFalse)) {
                localItem.addField("isSupply", fields.get("isSupply"));
            } else {
                throw new RulesetViolationException("isSupply must be a boolean: true or false");
            }
        }
    }
}
