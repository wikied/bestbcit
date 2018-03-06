package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;

public class CommonItemBuilderRulesetFactory implements ItemBuilderRulesetFactory {
    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return new CommonItemBuilderRuleset();
    }
}
