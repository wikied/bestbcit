package com.scriptofan.ecommerce.LocalItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LocalItemFactory {

    Collection<ItemBuilderRuleset>  itemBuilderRulesets;





    /*
     * For each Map in the list of maps, this needs to:
     *
     * 1) Create a new LocalItem
     * 2) Validate all the fields in the associated Map
     * 3) Use those fields to populate the fields in the LocalItem
     * 4) Create the LocalItem's Offer placeholders
     * 5) Set the initial quantity
     */
    public List<LocalItem> createLocalItems(final List<Map<String, String>> itemFieldCollection) {
        ArrayList<LocalItem> localItems;
        localItems = new ArrayList<>();

        getItemBuilderRulesets();

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
    private LocalItem createLocalItem(final Map<String, String> fields) {
        LocalItem localItem = new LocalItem();

        for (ItemBuilderRuleset ruleset : this.itemBuilderRulesets) {
            localItem = ruleset.apply(localItem, fields);
        }

        return localItem;
    }






    private void getItemBuilderRulesets() {
        if (this.itemBuilderRulesets == null) {

            this.itemBuilderRulesets = new ArrayList<ItemBuilderRuleset>();

        }
    }

}
