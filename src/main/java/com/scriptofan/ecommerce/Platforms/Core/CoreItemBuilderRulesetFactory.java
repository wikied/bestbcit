package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;

public class CoreItemBuilderRulesetFactory implements ItemBuilderRulesetFactory {
    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return new CoreItemBuilderRuleset();
    }
}
