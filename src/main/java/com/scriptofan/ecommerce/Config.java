package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Platforms.Core.CoreRepository;
import com.scriptofan.ecommerce.Platforms.Ebay.EbayPlatformRepository;
import com.scriptofan.ecommerce.Platforms.Etsy.EtsyPlatformRepository;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
@Service
public class Config {

    private static boolean initialized = false;

    public static final boolean forceEtsyDraft = true;

    @Autowired
    private PlatformRegistry platformRegistry;

    /*
     * Constructor. Creates a new PlatformRegistry and calls the init()
     * method.
     */
    public Config() {
        try {
            if (this.platformRegistry == null) {
                this.platformRegistry = new PlatformRegistry();
            }
            this.init();
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        }
        catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * The platform initialization script. Adds repositories (wrappers
     * for external ecommerce platforms) to the application's configuration.
     *
     * Please note: this is not done with Spring configuration, but that
     * might be a better solution.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public void init() throws AlreadyBoundException {
        if (Config.isInitialized()) { return; }

        Config.initialized = true;
        System.err.println("Initializing config");

        platformRegistry.setQuantityDistributionScheme(new DistributionCalculator());

        // Import PlatformRepositories here
        try {
            platformRegistry.registerPlatformRepository(new CoreRepository());
            platformRegistry.registerPlatformRepository(new EbayPlatformRepository());
            platformRegistry.registerPlatformRepository(new EtsyPlatformRepository());
        }
        catch (AlreadyRegisteredException e) {
            System.err.println("Trying to register the same repository more than once");
            e.printStackTrace();
        }

    }


    /**
     * Checks whether or not the application is initialized.
     * @return whether or not the platform has been initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
