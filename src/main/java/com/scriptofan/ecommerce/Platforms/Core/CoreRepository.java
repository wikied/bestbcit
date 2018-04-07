package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;

/**
 * The repository, or wrapper object, that is used to associate all the
 * core requirements for LocalItems with our application. By adding this
 * to the application's Config file, any core features for building items
 * will be added.
 */
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
