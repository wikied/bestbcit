package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import org.springframework.stereotype.Repository;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
@Repository
public final class Config {

    private static boolean initialized = false;

    /*
     * Constructor.
     */
    public Config() {
        try {
            Config.init();
        } catch (AlreadyInitializedException e) {
            System.err.println("Configuration already initialized");
        }
    }



    /**
     * The platform initialization script.
     * Import all platform repositories here.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public static void init() throws AlreadyInitializedException {
        /* Ensures init() is only run once per application. */
        if (Config.isInitialized()) { throw new AlreadyInitializedException(); }
        Config.initialized = true;

        System.err.println("Initializing config");

        // Import PlatformRepositories here
    }




    /**
     * Checks whether or not the application is initialized.
     * @return whether or not the platform has been initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
