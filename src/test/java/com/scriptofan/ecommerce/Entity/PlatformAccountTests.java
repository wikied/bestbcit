package com.scriptofan.ecommerce.Entity;

import com.scriptofan.ecommerce.Exception.AccountAlreadyAssociatedException;
import com.scriptofan.ecommerce.Exception.AccountIdAlreadySetException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformAccountTests {

    @Test
    public void canCreateBlankAccount() {

        PlatformAccount dummy = new DummyPlatformAccount();

        Assert.isNull(dummy.getAssociatedUser());
        Assert.isNull(dummy.getPlatformAccountId());
    }

    @Test
    public void canCreateAccountWithId() {
        PlatformAccount dummy = new DummyPlatformAccount("someAccountId");

        Assert.hasText("someAccountId", dummy.getPlatformAccountId());
        Assert.isNull(dummy.getAssociatedUser());
    }

    @Test
    public void canCreateAccountWithUser() {
        User user = new User();
        PlatformAccount dummy = new DummyPlatformAccount(user);

        Assert.isNull(dummy.getPlatformAccountId());
        Assert.notNull(dummy.getAssociatedUser());
        Assert.isInstanceOf(User.class, dummy.getAssociatedUser());
    }

    @Test
    public void canCreateAccountWithUserAndId() {
        User user = new User();
        PlatformAccount dummy = new DummyPlatformAccount("someId", user);

        Assert.hasText("someId", dummy.getPlatformAccountId());
        Assert.notNull(dummy.getAssociatedUser());
        Assert.isInstanceOf(User.class, dummy.getAssociatedUser());
    }

    @Test
    public void canAssociateUser() {
        User user = new User();
        PlatformAccount dummy = new DummyPlatformAccount();

        Assert.isNull(dummy.getAssociatedUser());

        try {
            dummy.setAssociatedUser(user);
        } catch (AccountAlreadyAssociatedException ex) {
            // Handle exception
        }

        Assert.isTrue(dummy.getAssociatedUser() == user);
    }

    @Test
    public void cannotReassociateUser() {
        User user1 = new User();
        User user2 = new User();
        PlatformAccount dummy = new DummyPlatformAccount(user1);

        Assert.isTrue(dummy.getAssociatedUser() == user1);

        try {
            dummy.setAssociatedUser(user2);
        } catch (AccountAlreadyAssociatedException ex) {
            // handle exception
        }

        Assert.isTrue(dummy.getAssociatedUser() == user1);
    }

    @Test
    public void canAssociateId() {
        PlatformAccount dummy = new DummyPlatformAccount();

        Assert.isNull(dummy.getPlatformAccountId());

        try {
            dummy.setPlatformAccountId("hihi");
        } catch (AccountIdAlreadySetException ex) {
            // Handle exception
        }

        Assert.isTrue(dummy.getPlatformAccountId().equals("hihi"));
    }

    @Test
    public void cannotReassociateId() {
        PlatformAccount dummy = new DummyPlatformAccount("hihi");

        Assert.isTrue(dummy.getPlatformAccountId().equals("hihi"));

        try {
            dummy.setPlatformAccountId("booga");
        } catch (AccountIdAlreadySetException ex) {
            // handle exception
        }

        Assert.isTrue(dummy.getPlatformAccountId().equals("hihi"));
    }

}

/**
 * Dummy class for testing abstract class PlatformAccount.
 */
class DummyPlatformAccount extends PlatformAccount {
    public DummyPlatformAccount() {
        super();
    }

    public DummyPlatformAccount(String platformAccountId) {
        super(platformAccountId);
    }

    public DummyPlatformAccount(User associatedUser) {
        super(associatedUser);
    }

    public DummyPlatformAccount(String platformAccountId, User associatedUser) {
        super(platformAccountId, associatedUser);
    }

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }
}
