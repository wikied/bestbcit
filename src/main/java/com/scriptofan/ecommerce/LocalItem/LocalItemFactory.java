package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class LocalItemFactory {

    public static final String LOG_APPLYING_RULESET = "Applying ruleset ";

    @Autowired
    private PlatformRegistry platformRegistry;

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
            throws  RulesetCollisionException,
                    RulesetViolationException,
                    NotImplementedException
    {
        ArrayList<LocalItem> localItems;

        if (itemFieldCollection == null) {
            throw new NullPointerException("ItemFieldCollection must not be null");
        }

        localItems = new ArrayList<>();
        for (Map<String, String> fields : itemFieldCollection) {

            if (fields == null) {
                throw new NullPointerException("itemFieldCollection container contains null values");
            }

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
            throws RulesetCollisionException,
                    RulesetViolationException,
                    NotImplementedException
    {
        LocalItem                       localItem;
        Collection<ItemBuilderRuleset>  rulesets;

        // Guard against being passed a null value.
        if (fields == null) {
            throw new NullPointerException("fields can't be null");
        }

        localItem = new LocalItem();

        // Run this item through each loaded Ruleset
        rulesets = platformRegistry.getItemBuilderRulesets();
        localItem.log("Applying " + rulesets.size() + " rulesets");
        if (rulesets != null) {
            for (ItemBuilderRuleset ruleset : rulesets) {
                localItem = ruleset.apply(localItem, fields);
            }
        }
        return localItem;
    }
}
