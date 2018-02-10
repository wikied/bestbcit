package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.AccountAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.AccountIdAlreadySetException;

/**
 * An account on a retail plaform (Etsy, Ebay).
 */
public abstract class PlatformAccount {

    // Member Variables
    private String  platformAccountId;
    private User    associatedUser;

    // Abstract Methods
    public abstract String getAccessToken();
    public abstract void setAccessToken(String accessToken);

    /**
     * Default constructor.
     */
    public PlatformAccount() {}

    /**
     * Constructor. Sets platform account unique ID.
     *
     * @param platformAccountId Unique ID to associate with platform account.
     */
    public PlatformAccount(String platformAccountId) {
        this.platformAccountId = platformAccountId;
    }

    /**
     * Constructor. Sets associated user.
     *
     * @param associatedUser User to associate with platform account.
     */
    public PlatformAccount(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    /**
     * Constructor. Sets associated user and platform account.
     *
     * @param platformAccountId Unique ID to associate with platform account.
     * @param associatedUser User to associate with platform account.
     */
    public PlatformAccount(String platformAccountId, User associatedUser) {
        this.platformAccountId = platformAccountId;
        this.associatedUser = associatedUser;
    }

    /**
     * Returns the user associated with this platform account.
     *
     * @return User associated with this platform account.
     */
    public final User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * Associates a user with this platform account, if none has
     * already been associated.
     *
     * @param associatedUser User to associate with this account.
     * @throws AccountAlreadyAssociatedException There is already
     * a User associated with this platform account.
     */
    public final void setAssociatedUser(User associatedUser)
        throws AccountAlreadyAssociatedException
    {
        if (this.associatedUser == null) {
            this.associatedUser = associatedUser;
        } else {
            throw new AccountAlreadyAssociatedException();
        }
    }

    /**
     * Returns the platform account's unique ID.
     *
     * @return Platform account's unique ID.
     */
    public final String getPlatformAccountId() {
        return platformAccountId;
    }

    /**
     * Sets the platform account's unique ID, or throws exception
     * (AccountIdAlreadySetException) if the ID has already been set.
     *
     * @param platformAccountId Unique ID to associate with platform account.
     * @throws AccountIdAlreadySetException account already has an ID set.
     */
    public final void setPlatformAccountId(String platformAccountId)
        throws AccountIdAlreadySetException
    {
        if (this.platformAccountId == null) {
            this.platformAccountId = platformAccountId;
        } else {
            throw new AccountIdAlreadySetException();
        }
    }
}
