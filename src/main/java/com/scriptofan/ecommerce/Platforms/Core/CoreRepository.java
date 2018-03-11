package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;

public class CoreRepository implements PlatformRepository {
    @Override
    public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() {
        return new CoreItemBuilderRulesetFactory();
    }

    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return getItemBuilderRulesetFactory().getNewItemBuilderRuleset();
    }
}
