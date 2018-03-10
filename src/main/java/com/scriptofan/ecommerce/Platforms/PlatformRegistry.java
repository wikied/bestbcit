package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.ItemDistributor.QuantityDistributionScheme;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;
import org.springframework.stereotype.Repository;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides a central access point to all registered retail platform
 * modules, and provides a single place to register new retail platform
 * modules with the application.
 */
@Repository
public class PlatformRegistry {

    private static QuantityDistributionScheme     quantityDistributionScheme;
    private static Collection<PlatformRepository> platformRepositories;
    static {
        platformRepositories = new ArrayList<>();
    }



    /**
     * Registers a PlatformRegistry with the application.
     * Please note: We recommend only registering platformRepositories in
     * Config.init(). Doing so keeps everything in one place.
     * @throws AlreadyRegisteredException the repository you're attempting
     * to add is already registered.
     */
    public void registerPlatformRepository(PlatformRepository platformRepository)
            throws  AlreadyRegisteredException,
                    NullPointerException
    {
        /* Catch null values */
        if (platformRepository == null) {
            throw new NullPointerException("platformRepository cannot be null");
        }

        /* Catch repositories of the same class */
        if (platformRepositories.size() > 0) {
            for (PlatformRepository repo : platformRepositories) {
                if (platformRepository.getClass().equals(repo.getClass())) {
                    throw new AlreadyRegisteredException(
                            platformRepository + " is already registered with the application.");
                }
            }
        }

        platformRepositories.add(platformRepository);
    }





    /**
     * Returns a collection of newly instantiated ItemBuilderRulesets, one from each
     * registered PlatformRepository.
     * @return collection of all registered ItemBuilderRulesets.
     */
    public Collection<ItemBuilderRuleset> getItemBuilderRulesets()
    {
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
    public Collection<ItemBuilderRulesetFactory> getItemBuilderRulesetFactories()
    {
        Collection<ItemBuilderRulesetFactory> rulesetFactories;

        rulesetFactories = new ArrayList<>();
        for (PlatformRepository repository : PlatformRegistry.platformRepositories) {
            rulesetFactories.add(repository.getItemBuilderRulesetFactory());
        }
        return rulesetFactories;
    }




    public static QuantityDistributionScheme getQuantityDistributionScheme() {
        return quantityDistributionScheme;
    }



    public static void setQuantityDistributionScheme(QuantityDistributionScheme quantityDistributionScheme)
            throws AlreadyBoundException
    {
        if (PlatformRegistry.quantityDistributionScheme == null) {
            PlatformRegistry.quantityDistributionScheme = quantityDistributionScheme;
        }
        else {
            throw new AlreadyBoundException("Distribution scheme is already set");
        }
    }
}
