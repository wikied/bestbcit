package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.LocalItem.ItemBuilderRulesetFactory;

public interface PlatformRepository {

    /**
     * Returns this Platform's ItemBuilderRulesetFactory
     * @return
     */
    ItemBuilderRulesetFactory getItemBuilderRulesetFactory();

}
