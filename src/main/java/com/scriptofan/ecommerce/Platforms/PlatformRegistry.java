package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRuleset;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Core.PlatformRepository;

import java.util.ArrayList;
import java.util.Collection;

public class PlatformRegistry {
    private static Collection<PlatformRepository> platformRepositories;
    static {
        platformRepositories = new ArrayList<>();
    }




    /*
     * Registers a PlatformRegistry with the application.
     */
    public static void registerPlatformRepository(PlatformRepository repository)
            throws AlreadyRegisteredException {

        if (platformRepositories.contains(repository)) {
            throw new AlreadyRegisteredException(repository + " is already registered with the application.");
        }
        platformRepositories.add(repository);
    }





    /**
     * Returns a collection of newly instantiated ItemBuilderRulesets, one from each
     * registered PlatformRepository.
     * @return collection of all registered ItemBuilderRulesets.
     */
    public static Collection<ItemBuilderRuleset> getItemBuilderRulesets() {
        Collection<ItemBuilderRuleset> rulesets;
        rulesets = new ArrayList<>();
        for (PlatformRepository repository : platformRepositories) {
            rulesets.add(repository.getNewItemBuilderRuleset());
        }
        return rulesets;
    }




    /**
     * Returns all registered itemBuilderRulesetFactories.
     * @return all registered itemBuilderRulesetFactories.
     */
    public static Collection<ItemBuilderRulesetFactory> getItemBuilderRulesetFactories() {
        Collection<ItemBuilderRulesetFactory> rulesetFactories;
        rulesetFactories = new ArrayList<>();
        for (PlatformRepository repository : PlatformRegistry.platformRepositories) {
            rulesetFactories.add(repository.getItemBuilderRulesetFactory());
        }

        return rulesetFactories;
    }

}
