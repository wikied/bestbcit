package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.AccountAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.ManagerAlreadyAssociatedException;

import java.util.Collection;
import java.util.Map;

/**
 * Aggregate that manages all platform accounts for a user.
 */
public class PlatformAccountManager {

    private User                            associatedUser;
    private Map<String, PlatformAccount>    accounts;

    /**
     * Default constructor.
     */
    public PlatformAccountManager() { }

    /**
     * Constructor. Associates a User with the PlatformAccountManager.
     *
     * @param associatedUser User to associate with this PlatformAccountManager.
     */
    public PlatformAccountManager(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    /**
     * Constructor. Associates a User with the PlatformAccountManager.
     *
     * @param associatedUser User to associate with this PlatformAccountManager.
     * @param associatedUser User to associate with this PlatformAccountManager.
     */
    public PlatformAccountManager(
        User                            associatedUser,
        Map<String, PlatformAccount>    accounts
    ) {
        this.associatedUser = associatedUser;
        this.accounts = accounts;
    }

    /**
     * Returns the User associated with this PlatformAccountManager.
     *
     * @return User associated with this PlatformAccountManager.
     */
    public User getAssociatedUser() {
        return associatedUser;
    }

    /**
     * Associates a User with this PlatformAccountManager.
     * Note: This will only succeed if no user is already
     * associated.
     *
     * @param associatedUser User to associate with this PlatformAccountManager.
     * @throws ManagerAlreadyAssociatedException There is already a user
     * associated with this PlatformAccountManager.
     */
    public void setAssociatedUser(User associatedUser)
        throws ManagerAlreadyAssociatedException
    {
        if (this.associatedUser == null) {
            this.associatedUser = associatedUser;
        } else {
            throw new ManagerAlreadyAssociatedException();
        }
    }

    /**
     * Returns a collection of all platform accounts managed by
     * this PlatformAccountManager.
     *
     * @return All accounts managed by this PlatformAccountManager.
     */
    public Collection<PlatformAccount> getAccounts() {
        return accounts.values();
    }

    /**
     * Initializes this PlatformAccountManager's internal accounts
     * with a provided map.
     *
     * @param accounts
     * @throws AccountAlreadyAssociatedException
     */
    public void setAccounts(Map<String, PlatformAccount> accounts)
        throws AccountAlreadyAssociatedException
    {
        if (this.accounts == null) {
            // TODO: Add account validation.
            this.accounts = accounts;
        } else {
            throw new AccountAlreadyAssociatedException();
        }
    }
}
