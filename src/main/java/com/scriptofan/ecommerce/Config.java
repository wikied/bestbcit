package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.LocalItem.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Core.PlatformRepository;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
public class Config {

    private static boolean initialized = false;

    /**
     * The platform initialization script.
     * Import all platform repositories here.
     * @throws AlreadyInitializedException if init() was already called.
     */
    public static void init()
            throws AlreadyInitializedException {
        // Ensures init() is only run once per application.
        if (Config.initialized) {
            throw new AlreadyInitializedException();
        }
        Config.initialized = true;



        // Import PlatformRepositories

    }




    /*
     * Checks whether or not the application is initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
