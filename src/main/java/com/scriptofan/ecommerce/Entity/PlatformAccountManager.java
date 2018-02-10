package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.AccountAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.MalformedAccountMapException;
import com.scriptofan.ecommerce.Exception.ManagerAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.NoSuchPlatformAccountException;
import javafx.application.Platform;

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
    public PlatformAccountManager() {}

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
     * @param accountMap Map of Platform Accounts to associate with this PlatformAccountManager.
     * @throws MalformedAccountMapException Account map is malformed.
     */
    public PlatformAccountManager(User                          associatedUser,
                                  Map<String, PlatformAccount>  accountMap)
        throws MalformedAccountMapException
    {
        validateAccountMap(accountMap);

        this.associatedUser = associatedUser;
        this.accounts       = accountMap;
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
     * @param accountMap Map of accounts to add.
     * @throws AccountAlreadyAssociatedException
     */
    public void setAccounts(Map<String, PlatformAccount> accountMap)
        throws  AccountAlreadyAssociatedException,
                MalformedAccountMapException
    {
        if (this.accounts == null) {
            validateAccountMap(accountMap);
            this.accounts = accountMap;
        } else {
            throw new AccountAlreadyAssociatedException();
        }
    }

    /*
     * Validates a map of accounts. Throws a MalformedAccountMapException if
     * any of the keys are not equal to the associated PlatformAccount.accountId values.
     */
    private static void validateAccountMap(Map<String, PlatformAccount> accountMap)
        throws MalformedAccountMapException
    {
        String keyId;
        String accountId;

        for (Map.Entry<String, PlatformAccount> entry : accountMap.entrySet()) {

            keyId       = entry.getKey();
            accountId   = entry.getValue().getPlatformAccountId();

            if (!keyId.equals(accountId)) {
                throw new MalformedAccountMapException();
            }
        }
    }

    /**
     * Returns platform account associated with specified accountId.
     *
     * @param accountId Account ID of account to retrieve.
     * @return PlatformAccount associated with specified accountID.
     * @throws NoSuchPlatformAccountException No account associated with
     * specified accountId.
     */
    public PlatformAccount getPlatformAccountById(String accountId)
        throws NoSuchPlatformAccountException
    {
        if (this.accounts.containsKey(accountId)) {
            return this.accounts.get(accountId);
        } else {
            throw new NoSuchPlatformAccountException();
        }
    }
}
