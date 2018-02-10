package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.AccountAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.ManagerAlreadyAssociatedException;

import java.util.HashSet;
import java.util.Set;

/**
 * Aggregate that manages all platform accounts for a user.
 */
public class PlatformAccountManager {

    private User                    associatedUser;
    private Set<PlatformAccount>    accounts;

    public PlatformAccountManager() { }

    public PlatformAccountManager(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public PlatformAccountManager(User associatedUser, Set<PlatformAccount> accounts) {
        this.associatedUser = associatedUser;
        this.accounts = accounts;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser)
        throws ManagerAlreadyAssociatedException
    {
        if (this.associatedUser == null) {
            this.associatedUser = associatedUser;
        } else {
            throw new ManagerAlreadyAssociatedException();
        }
    }

    public Set<PlatformAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<PlatformAccount> accounts)
        throws AccountAlreadyAssociatedException
    {
        if (this.accounts == null) {
            this.accounts = accounts;
        } else {
            throw new AccountAlreadyAssociatedException();
        }
    }
}
