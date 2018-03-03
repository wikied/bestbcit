package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.LocalItem.CommonItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRulesetFactory;

import java.util.ArrayList;
import java.util.Collection;

public class PlatformRepository {

    Collection<ItemBuilderRulesetFactory> itemBuilderRulesetFactories;

    /*
     * Constructor. Initializes collection of ruleset factories.
     */
    public PlatformRepository() {
        this.itemBuilderRulesetFactories = new ArrayList<>();
        this.itemBuilderRulesetFactories.add(new CommonItemBuilderRulesetFactory());
    }


    public Collection<ItemBuilderRulesetFactory> getItemBuilderRulesetFactories() {
        return this.itemBuilderRulesetFactories;
    }

}
