package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
@Repository
public class Config {

    private static boolean initialized = false;

    @Autowired
    private PlatformRegistry platformRegistry;

    /*
     * Constructor.
     */
    public Config() {
        Config.init();
    }



    /**
     * The platform initialization script.
     * Import all platform repositories here.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public static void init()
    {
        if (Config.isInitialized()) { return; }
        Config.initialized = true;
        System.err.println("Initializing config");




    }




    /**
     * Checks whether or not the application is initialized.
     * @return whether or not the platform has been initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
