package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Platforms.Core.CoreRepository;
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
        try {
            init();
        } catch (AlreadyInitializedException | AlreadyRegisteredException e) {
            System.err.println("Configuration already initialized");
        } catch (NullPointerException e) {
            System.err.println("Null Pointer");
            e.printStackTrace();
        }
    }



    /**
     * The platform initialization script.
     * Import all platform repositories here.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public void init()
            throws  AlreadyInitializedException,
                    AlreadyRegisteredException
    {
        /* Ensures init() is only run once per application. */
        if (Config.isInitialized()) { throw new AlreadyInitializedException(); }
        Config.initialized = true;

        System.err.println("Initializing config");

        // Import PlatformRepositories here
        platformRegistry.registerPlatformRepository(new CoreRepository());
    }




    /**
     * Checks whether or not the application is initialized.
     * @return whether or not the platform has been initialized.
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
