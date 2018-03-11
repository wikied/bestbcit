package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;

public class EbayPlatformRepository implements PlatformRepository {
    @Override
    public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() {
        return null;
    }

    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return new EbayItemBuilderRuleset();
    }
}
