package com.scriptofan.ecommerce.Platforms.Interface;

import com.scriptofan.ecommerce.LocalItem.ItemBuilderRuleset;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRulesetFactory;

/**
 * Represents the entry point into a retail platform module (i.e.,
 * "EbayWrapper," "EtsyWrapper," etc.).
 *
 * PlatformRepositories are used to register your retail platform
 * module with the application. The application then uses these
 * to request all the necessary things it needs to run for your
 * given retail platform.
 */
public interface PlatformRepository {
    /**
     * Returns this Platform's ItemBuilderRulesetFactory.
     * @return this Platform's ItemBuilderRulesetFactory.
     */
    ItemBuilderRulesetFactory getItemBuilderRulesetFactory();



    /**
     * Returns a new instance of this Platform's ItemBuilderRuleset.
     * @return a new instance of this Platform's ItemBuilderRuleset.
     */
    ItemBuilderRuleset getNewItemBuilderRuleset();
}
