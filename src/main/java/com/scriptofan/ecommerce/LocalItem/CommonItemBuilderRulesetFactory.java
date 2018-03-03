package com.scriptofan.ecommerce.LocalItem;

public class CommonItemBuilderRulesetFactory implements ItemBuilderRulesetFactory {
    @Override
    public ItemBuilderRuleset getNewItemBuilderRuleset() {
        return new CommonItemBuilderRuleset();
    }
}
