package com.scriptofan.ecommerce.internal.user.Entity;

import com.scriptofan.ecommerce.internal.user.Exception.DuplicatePlatformAccountIdException;
import com.scriptofan.ecommerce.internal.user.Exception.MalformedPlatformAccountException;
import com.scriptofan.ecommerce.internal.user.Exception.ManagerAlreadyAssociatedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformAccountManagerTests {

    @Test
    public void createEmptyPlatformAccountManager() {

        PlatformAccountManager manager = new PlatformAccountManager();

        Assert.isNull(manager.getAssociatedUser());
        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "get() returns empty collection of PlatformAccounts");
    }

    @Test
    public void createPlatformAccountManagerWithAssociatedUser() {

        User user = new User();
        PlatformAccountManager manager = new PlatformAccountManager(user);

        Assert.isTrue(manager.getAssociatedUser() == user);
        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "get() returns empty collection of PlatformAccounts");
    }

    @Test
    public void canAssociateUser() {

        User user = new User();
        PlatformAccountManager manager = new PlatformAccountManager();

        Assert.isNull(manager.getAssociatedUser());

        try {
            manager.setAssociatedUser(user);
        } catch ( ManagerAlreadyAssociatedException e) {
            // Handle exception.
        }

        Assert.isTrue(manager.getAssociatedUser() == user);
    }

    @Test
    public void cannotReassociateUser() {

        boolean didThrowException = false;
        User user1 = new User();
        User user2 = new User();
        PlatformAccountManager manager = new PlatformAccountManager(user1);

        try {
            manager.setAssociatedUser(user2);
        } catch (ManagerAlreadyAssociatedException ex){
            didThrowException = true;
        }

        Assert.isTrue(didThrowException, "Trying to reassociate user did throw an exception");
        Assert.isTrue(manager.getAssociatedUser() == user1);
    }

    @Test
    public void canAddPlatformAccount() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException  = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy               = new DummyPlatformAccount("1234", user);

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(!threwDuplicateAccountException);
        Assert.isTrue(!threwMalformedAccountException);

        Assert.isTrue(manager.getAllAccounts().size() == 1);

        Assert.isTrue(manager.getAllAccounts().contains(dummy));
        Assert.isTrue(manager.getAccount("1234") == dummy);
    }

    @Test
    public void canAddMultiplePlatformAccounts() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy1;
        PlatformAccount         dummy2;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException   = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy1              = new DummyPlatformAccount("1234", user);
        dummy2              = new DummyPlatformAccount("5678", user);

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy1, dummy2);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(!threwMalformedAccountException);
        Assert.isTrue(manager.getAllAccounts().size() == 2,
                "After add, geAllAccounts().size() == 2");
        Assert.isTrue(manager.getAllAccounts().contains(dummy1));
        Assert.isTrue(manager.getAllAccounts().contains(dummy2));
        Assert.isTrue(manager.getAccount("1234") == dummy1);
        Assert.isTrue(manager.getAccount("5678") == dummy2);
    }

    @Test
    public void addShouldFailIfNotAssociatedWithUser() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException   = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy               = new DummyPlatformAccount("1234");

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(threwMalformedAccountException);
        Assert.isTrue(manager.getAllAccounts().size() == 0);
        Assert.isTrue(!manager.getAllAccounts().contains(dummy));
        Assert.isTrue(manager.getAccount("1234") == null);
    }

    @Test
    public void addMultipleShouldFailIfNotAssociatedWithUser() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy1;
        PlatformAccount         dummy2;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException  = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy1              = new DummyPlatformAccount("1234", user);
        dummy2              = new DummyPlatformAccount("5678");

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy1, dummy2);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(threwMalformedAccountException);
        Assert.isTrue(manager.getAllAccounts().size() == 0);
        Assert.isTrue(!manager.getAllAccounts().contains(dummy1));
        Assert.isTrue(!manager.getAllAccounts().contains(dummy2));
        Assert.isTrue(manager.getAccount("1234") == null);
        Assert.isTrue(manager.getAccount("5678") == null);
    }

    @Test
    public void addShouldFailIfConflictingIds() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy1;
        PlatformAccount         dummy2;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException  = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy1              = new DummyPlatformAccount("1234", user);
        dummy2              = new DummyPlatformAccount("1234", user);

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy1);
            manager.add(dummy2);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(threwDuplicateAccountException);
        Assert.isTrue(!threwMalformedAccountException);
        Assert.isTrue(manager.getAllAccounts().size() == 1);
        Assert.isTrue(manager.getAllAccounts().contains(dummy1));
        Assert.isTrue(!manager.getAllAccounts().contains(dummy2));
        Assert.isTrue(manager.getAccount("1234") == dummy1);
        Assert.isTrue(manager.getAccount("5678") == null);
    }

    @Test
    public void addMultipleShouldFailIfConflictingIds() {

        boolean                 threwMalformedAccountException;
        boolean                 threwDuplicateAccountException;
        PlatformAccount         dummy1;
        PlatformAccount         dummy2;
        User                    user;
        PlatformAccountManager  manager;

        threwMalformedAccountException  = false;
        threwDuplicateAccountException  = false;

        user                = new User();
        manager             = new PlatformAccountManager(user);
        dummy1              = new DummyPlatformAccount("1234", user);
        dummy2              = new DummyPlatformAccount("1234", user);

        Assert.isTrue(manager.getAllAccounts().size() == 0,
                "getAllAccounts() returns empty collection of PlatformAccounts");

        try {
            manager.add(dummy1, dummy2);
        } catch (MalformedPlatformAccountException e) {
            threwMalformedAccountException = true;
        } catch (DuplicatePlatformAccountIdException e) {
            threwDuplicateAccountException = true;
        }

        Assert.isTrue(threwDuplicateAccountException);
        Assert.isTrue(!threwMalformedAccountException);
        Assert.isTrue(manager.getAllAccounts().size() == 0);
        Assert.isTrue(!manager.getAllAccounts().contains(dummy1));
        Assert.isTrue(!manager.getAllAccounts().contains(dummy2));
        Assert.isTrue(manager.getAccount("1234") == null);
        Assert.isTrue(manager.getAccount("5678") == null);
    }

}