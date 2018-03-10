package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Platforms.Core.CoreRepository;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Configuration module. Try to centralize all build configurations here,
 * particularly for handling the integration of new retail platform modules.
 */
@Service
public class Config {

    private static boolean initialized = false;

    @Autowired
    private PlatformRegistry platformRegistry;

    /*
     * Constructor.
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
    }



    /**
     * The platform initialization script.
     * Import all platform repositories here.
     *
     * @throws AlreadyInitializedException if init() was already called.
     */
    public void init()
    {
        if (Config.isInitialized()) { return; }

        Config.initialized = true;
        System.err.println("Initializing config");

        // Import PlatformRepositories here
        try {
            platformRegistry.registerPlatformRepository(new CoreRepository());
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
