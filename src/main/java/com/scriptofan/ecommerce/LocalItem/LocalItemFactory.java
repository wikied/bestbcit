package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalItemFactory {
    /*
     * For each Map in the list of maps, this needs to:
     *
     * 1) Create a new LocalItem
     * 2) Validate all the fields in the associated Map
     * 3) Use those fields to populate the fields in the LocalItem
     * 4) Create the LocalItem's Offer placeholders
     * 5) Set the initial quantity
     */

    public List<LocalItem> createLocalItems(final List<Map<String, String>> itemFieldCollection)
            throws RulesetCollisionException {
        ArrayList<LocalItem> localItems = new ArrayList<>();

        if (itemFieldCollection == null) {
            throw new NullPointerException();
        }

        for (Map<String, String> fields : itemFieldCollection) {
            LocalItem newLocalItem = createLocalItem(fields);
            localItems.add(newLocalItem);
        }
        return localItems;
    }






    /**
     * Creates a new local item and applies each ItemBuilderRuleset to it.
     * @return New LocalItem created from fields.
     */
    private LocalItem createLocalItem(final Map<String, String> fields)
            throws RulesetCollisionException {

        LocalItem localItem = new LocalItem();

        // Guard against being passed a null value.
        if (fields == null) {
            throw new NullPointerException("fields can't be null");
        }

        // Run this item through each loaded Ruleset
        for (ItemBuilderRuleset ruleset : PlatformRegistry.getItemBuilderRulesets()) {
            localItem = ruleset.apply(localItem, fields);
        }

        return localItem;
    }
}
