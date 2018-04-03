package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Ebay.InventoryItem.Entity.ConditionEnum;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.CurrencyCode;
import com.scriptofan.ecommerce.Platforms.Ebay.Offer.MarketplaceEnum;
import com.scriptofan.ecommerce.Platforms.Etsy.EtsyListingBuilderRule;
import com.scriptofan.ecommerce.Platforms.Etsy.EtsyLocalOffer;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {

    private static final String VALID_PRICE_FORMAT = "FIXED_PRICE";

    public static final EbayItemRule[] RULES = {
            new EbayItemRule("title", "productTitle", true),
            new EbayItemRule("description", "productDescription", true),
            new EbayItemRule("productImageUrls", "productImageUrls", true),
            new EbayItemRule("sku", "sku", true),
            new EbayItemRule("merchantLocationKey", "merchantLocationKey", true),
            new EbayItemRule("categoryId", "categoryId", true),
            new EbayItemRule("fulfillmentPolicy", "fulfillmentPolicy", true),
            new EbayItemRule("paymentPolicy", "paymentPolicy", true),
            new EbayItemRule("returnPolicy", "returnPolicy", true),
            new EbayItemRule("value", "value", true),
            new EbayItemRule("currencyCode", "currencyCode", true) {
                @Override
                public boolean validate(String value) throws RulesetViolationException {
                    super.validate(value);
                    if (CurrencyCode.currencies.contains(value)) {
                        return true;
                    }
                    else {
                        throw new RulesetViolationException(getKeyInternal() + " must be valid currency code");
                    }
                }
            },
            new EbayItemRule("marketplaceId", "marketplaceId", true) {
                @Override
                public boolean validate(String value) throws RulesetViolationException {
                    for (MarketplaceEnum marketplaceEnum : MarketplaceEnum.values()) {
                        if(value.equals(marketplaceEnum.toString())) {
                            return true;
                        }
                    }
                    throw new RulesetViolationException(getKeyInternal() + " must be valid MarketplaceEnum value.");
                }
            },
            new EbayItemRule("format", "format", true) {
                @Override
                public boolean validate(String value) throws RulesetViolationException {
                    super.validate(value);
                    if (!value.equals(VALID_PRICE_FORMAT)) {
                        throw new RulesetViolationException("Invalid format");
                    }
                    return true;
                }
            },
            new EbayItemRule("condition", "condition", true) {
                @Override
                public boolean validate(String value) throws RulesetViolationException {
                    super.validate(value);
                    for (ConditionEnum conditionEnum : ConditionEnum.values()) {
                        if(value.equals(conditionEnum.toString())) {
                            return true;
                        }
                    }
                    throw new RulesetViolationException(getKeyInternal() + " must be valid ConditionEnum value.");
                }
            },
    };

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
        String rulesetViolationReport = null;

        for (EbayItemRule rule : RULES) {
            final String field;
            final String internalKey;

            try {
                internalKey = rule.getKeyInternal();
                field       = fields.get(internalKey);
                rule.validate(field);
                localItem.addField(internalKey, rule.transform(field));
            }
            // Handle ruleset violations by collecting all violations first before throwing.
            catch (RulesetViolationException e) {
                if (rulesetViolationReport == null) {
                    rulesetViolationReport = "";
                }
                else {
                    rulesetViolationReport += "\n";
                }
                rulesetViolationReport += e.getMessage();
            }
        }

        if (rulesetViolationReport != null) {
            throw new RulesetViolationException(rulesetViolationReport);
        }
    }

    private void buildOffer(LocalItem localItem, Map<String, String> fields)
        throws RulesetCollisionException,
               RulesetViolationException {

        EbayLocalOffer ebayLocalOffer;


        ebayLocalOffer = new EbayLocalOffer(localItem);
        localItem.addOffer(ebayLocalOffer);
    }

}
