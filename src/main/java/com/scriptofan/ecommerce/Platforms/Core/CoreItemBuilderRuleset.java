package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

/**
 * Responsible for validating all LocalItem key fields used by the entire
 * application, and adding them to LocalItem objects.
 */
public class CoreItemBuilderRuleset implements ItemBuilderRuleset {

    public static final String TOTAL_QUANTITY = "totalQuantity";

    /**
     * Applies this Ruleset to the passed in LocalItem, pulling data from the
     * fields parameter.
     *
     * @param localItem LocalItem to add the validated fields to.
     * @param fields Source for data to validate and add to the LocalItem.
     * @return LocalItem that was passed in.
     * @throws RulesetViolationException
     */
    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws  RulesetViolationException
    {
        String  quantityStr;
        Integer quantityInt;

        if (!fields.containsKey(TOTAL_QUANTITY)) {
            throw new RulesetViolationException(
                    "Item specification must contain field \"" + TOTAL_QUANTITY + "\"");
        }

        quantityStr = fields.get(TOTAL_QUANTITY);
        quantityInt = Integer.parseInt(quantityStr);

        if (quantityInt == null || quantityInt < 0) {
            throw new RulesetViolationException(
                    "Field \"" + TOTAL_QUANTITY + "\" must be a non-negative integer.");
        }

        localItem.setTotalQuantity(quantityInt);
        return localItem;
    }



    public String toString() {
        return "CoreItemBuilderRuleset";
    }
}
