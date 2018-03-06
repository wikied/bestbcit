package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Platforms.Core.PlatformRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
public class Config {

    /*
     * Collection of all PlatformRepositories the application recognizes.
     * It is static because this is supposed to be application-wide.
     */
    private static Collection<PlatformRepository> platformRepositories = new ArrayList<>();
    private static boolean initialized = false;






    /*
     * The platform initialization script. Import all platform repositories here.
     */
    public static void init() throws AlreadyInitializedException {
        if (Config.initialized) { throw new AlreadyInitializedException(); }
        Config.initialized = true;

        // Import PlatformRepositories
    }





    /*
     * Adds a PlatformRepository to the configuration.
     */
    public static void importPlatformRepository(PlatformRepository repository) {
        Config.platformRepositories.add(repository);
    }

    /*
     * Checks whether or not the application is initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
