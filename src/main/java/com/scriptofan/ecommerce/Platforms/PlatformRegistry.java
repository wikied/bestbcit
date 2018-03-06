package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRuleset;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides a central access point to all registered retail platform
 * modules, and provides a single place to register new retail platform
 * modules with the application.
 */
public class PlatformRegistry {
    private static Collection<PlatformRepository> platformRepositories;
    static {
        platformRepositories = new ArrayList<>();
    }




    /**
     * Registers a PlatformRegistry with the application.
     *
     * Please note: We recommend only registering platformRepositories in
     * Config.init(). Doing so keeps everything in one place.
     *
     * @throws AlreadyRegisteredException the repository you're attempting
     * to add is already registered.
     */
    public static void registerPlatformRepository(PlatformRepository repository)
            throws AlreadyRegisteredException {

        if (platformRepositories.contains(repository)) {
            throw new AlreadyRegisteredException(
                    repository + " is already registered with the application.");
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
