package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

/**
 * This class builds the local item for Etsy by going through the fields
 * of the csv and adds the required fields to the local item
 **/
public class EtsyListingBuilderRuleset implements ItemBuilderRuleset {

    private boolean validWhoMade    = false;
    private static final String IS_SUPPLY_TRUE  = "true";
    private static final String IS_SUPPLY_FALSE = "false";

    /**
     * A collection of rules to apply to incoming LocalItem objects.
     */
    public static final EtsyListingBuilderRule[] RULES = {
        new EtsyListingBuilderRule("title", "title", true),
        new EtsyListingBuilderRule("description", "description", true),
        new EtsyListingBuilderRule("value", "price", true),
        new EtsyListingBuilderRule("shippingTemplateId", "shipping_template_id", true),
        new EtsyListingBuilderRule("currencyCode", "currency_code", true),
        new EtsyListingBuilderRule("whenMade", "when_made", true),
        new EtsyListingBuilderRule("state", "state", true) {
            /**
             * Force "state" to be "draft" (so we don't spend money on Etsy).
             * @param value
             * @return
             * @throws RulesetViolationException
             */
            @Override
            public boolean validate(String value) throws RulesetViolationException {
                if (!value.equals("draft")) {
                    throw new RulesetViolationException(getKeyInternal() + " must be \"draft\" (is \"" + value + "\")");
                }
                return true;
            }
        },
        new EtsyListingBuilderRule("whoMade", "who_made", true) {
            /**
             * WhoMade must be one of the values specified by WhoMadeEnum.
             * @param value String to be validated.
             * @return true if valid.
             * @throws RulesetViolationException
             */
            @Override
            public boolean validate(String value) throws RulesetViolationException {
                super.validate(value);
                for (WhoMadeEnum whoMadeEnum : WhoMadeEnum.values()) {
                    if(value.equals(whoMadeEnum.toString())) {
                        return true;
                    }
                }
                throw new RulesetViolationException(getKeyInternal() + " is not valid.");
            }
        },
        new EtsyListingBuilderRule("isSupply", "is_supply", true) {
            /**
             * "isSupply" must be either "true" or "false"
             * @param value String to validate.
             * @return true if valid
             * @throws RulesetViolationException
             */
            @Override
            public boolean validate(String value) throws RulesetViolationException {
                super.validate(value);
                if (value.equals(IS_SUPPLY_TRUE) || value.equals(IS_SUPPLY_FALSE)) {
                    return true;
                }
                else {
                    throw new RulesetViolationException(getKeyInternal() + " must be \"true\" or \"false\"");
                }
            }
        }
    };



    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException,
                   RulesetViolationException {
        validateAndAttachFields(localItem, fields);
        return localItem;
    }



    /*
     * Validates and attaches fields to the LocalItem.
     */
    private void validateAndAttachFields(LocalItem localItem, Map<String, String> fields)
            throws RulesetCollisionException, RulesetViolationException
    {
        String rulesetViolationReport = null;

        for (EtsyListingBuilderRule rule : RULES) {
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

        if (rulesetViolationReport == null) {
            EtsyLocalOffer etsyLocalOffer = new EtsyLocalOffer(localItem);
            localItem.addOffer(etsyLocalOffer);
        }
        else {
            throw new RulesetViolationException(rulesetViolationReport);
        }
    }
}