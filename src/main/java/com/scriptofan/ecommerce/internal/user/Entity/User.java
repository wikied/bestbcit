package com.scriptofan.ecommerce.internal.user.Entity;

import com.scriptofan.ecommerce.internal.user.Exception.AccountIdAlreadySetException;
import com.scriptofan.ecommerce.internal.user.Exception.ManagerAlreadyAssociatedException;

/**
 * A Scriptofan Ecommerce User. Aggregates all components of a user.
 */
public class User {

    // User ID
    private String                  uuid;

    // User Components
    // private UserCredentials         credentials;
    // private UserProfile             profile;
    // private Inventory               inventory;
    private PlatformAccountManager  platformAccountManager;

    // Constructors
    public User() {}
    public User(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns user's UUID.
     *
     * @return User's UUID.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets user's UUID. Fails if UUID is already set.
     *
     * @param uuid Value to set UUID to.
     * @throws AccountIdAlreadySetException UUID is already set.
     */
    public void setUuid(String uuid)
        throws AccountIdAlreadySetException
    {
        if (this.uuid == null)
        {
            this.uuid = uuid;
        }
        else
        {
            throw new AccountIdAlreadySetException("User ID is already set");
        }
    }

    /**
     * Returns this User's PlatformAccountManager (basically just the aggregator that
     * handles managing PlatformAccounts).
     *
     * @return PlatformAccountManager associated with this user.
     */
    public PlatformAccountManager getPlatformAccountManager() {
        return platformAccountManager;
    }

    /**
     * Sets this User's PlatformAccountManager (basically just the aggregator that
     * handles managing PlatformAccounts). Fails if PlatformAccountManager is already
     * set.
     *
     * @param platformAccountManager PlatformAccountManager to associate with user.
     * @throws ManagerAlreadyAssociatedException
     */
    public void setPlatformAccountManager(PlatformAccountManager platformAccountManager)
        throws ManagerAlreadyAssociatedException
    {

        if (this.platformAccountManager == null)
        {
            this.platformAccountManager = platformAccountManager;
            platformAccountManager.setAssociatedUser(this);

        }
        else
        {
            throw new ManagerAlreadyAssociatedException(
                "This User is already associated with a PlatformAccountManager");
        }
    }
}
