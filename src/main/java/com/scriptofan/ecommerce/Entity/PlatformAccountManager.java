package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.*;
import javafx.application.Platform;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
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
    public PlatformAccountManager() {
        accounts = new HashMap<String, PlatformAccount>();
    }

    /**
     * Constructor. Associates a User with the PlatformAccountManager.
     *
     * @param associatedUser User to associate with this PlatformAccountManager.
     */
    public PlatformAccountManager(User associatedUser) {
        this();
        this.associatedUser = associatedUser;
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

    public PlatformAccount getAccount( String id ) {
        PlatformAccount returnAccount = null;

        if (this.accounts.containsKey(id)) {
            returnAccount = this.accounts.get(id);
        }

        return returnAccount;
    }

    public Collection<PlatformAccount> getAllAccounts() {
        return this.accounts.values();
    }

    public boolean containsAccount( String id ) {
        return this.accounts.containsKey(id);
    }

    /**
     * Adds a PlatformAccount to this PlatformAccountManager.
     *
     * @param account
     * @throws MalformedPlatformAccountException PlatformAccount wasn't properly formed.
     * @throws DuplicatePlatformAccountIdException Duplicate accountID found.
     */
    public void add(PlatformAccount account)
        throws  MalformedPlatformAccountException,
                DuplicatePlatformAccountIdException
    {
        String accountId = account.getPlatformAccountId();

        if (this.accounts.containsKey(accountId)) {
            throw new DuplicatePlatformAccountIdException();
        }

        if (account.getAssociatedUser() != this.associatedUser) {
            throw new MalformedPlatformAccountException(
                "PlatformAccount User must be same as PlatformAccountManager user");
        }

        this.accounts.put(accountId, account);
    }

    /**
     * Adds multiple PlatformAccounts to PlatformAccountManager.
     *
     * @param accounts Variable list of PlatformAccounts.
     * @throws MalformedPlatformAccountException One of the PlatformAccounts wasn't properly formed.
     * @throws DuplicatePlatformAccountIdException Duplicate accountIDs found.
     */
    public void add(PlatformAccount... accounts)
        throws  MalformedPlatformAccountException,
                DuplicatePlatformAccountIdException
    {
        String                      accountId;
        Map<String,PlatformAccount> accountBuffer;

        accountBuffer = new HashMap<String, PlatformAccount>();

        for (PlatformAccount account : accounts) {
            accountId = account.getPlatformAccountId();

            // Duplicate ID already stored
            if (this.accounts.containsKey(accountId)) {
                throw new DuplicatePlatformAccountIdException(
                    "Listed accountID " + accountId + " already exists in account.");
            }

            // Duplicate account IDs were submitted
            if (accountBuffer.containsKey(accountId)) {
                throw new DuplicatePlatformAccountIdException(
                    "Accounts with duplicate accountIDs submitted: " + accountId);
            }

            // Associated user doesn't equal the PlatformAccountManager's user
            if (account.getAssociatedUser() != this.associatedUser) {
                throw new MalformedPlatformAccountException(
                        "PlatformAccount User must be same as PlatformAccountManager user");
            }

            accountBuffer.put(accountId, account);
        }

        this.accounts.putAll(accountBuffer);
    }

}
