package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;

public class EtsyPlatformRepository implements PlatformRepository {

    @Override
    public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() {
        return null;
    }

    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return null;
    }

}
