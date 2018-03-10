package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.stereotype.Repository;

import java.rmi.AlreadyBoundException;

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
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * The platform initialization script.
     * Import all platform repositories here.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public static void init() throws AlreadyInitializedException, AlreadyBoundException {
        /* Ensures init() is only run once per application. */
        if (Config.isInitialized()) { throw new AlreadyInitializedException(); }
        Config.initialized = true;

        System.err.println("Initializing config");

        (new PlatformRegistry()).setQuantityDistributionScheme(new DistributionCalculator());

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
