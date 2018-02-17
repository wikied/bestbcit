package com.scriptofan.ecommerce.internal.user.Entity;

import com.scriptofan.ecommerce.internal.user.Exception.InventoryAlreadyAssociatedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryTests {

    private Inventory inventory;

    @Before
    public void beforeEachTestMethod() {
        inventory = null;
    }

    // Inventory Not Associated With User
    @Test
    public void shouldHaveNoAssociatedUser() {
        inventory = new Inventory();
        Assert.isNull(inventory.getAssociatedUser());
    }

    @Test
    public void getAllShouldReturnEmptyCollection() {
        inventory = new Inventory();
        Assert.isTrue(inventory.getAll().size() == 0);
    }

    @Test
    public void shouldBeAbleToAssociateUser() {
        boolean exception   = false;
        User    user        = new User();
        inventory = new Inventory();

        try
        {
            inventory.setAssociatedUser(user);
        }
        catch (InventoryAlreadyAssociatedException e)
        {
            exception = true;
        }

        Assert.isTrue(!exception);
        Assert.isTrue(inventory.getAssociatedUser() == user);
    }

    // Inventory Associated With User
    @Test
    public void setAssociatedUserShouldFail() {
        boolean exception   = false;
        User testUser       = new User();

        inventory = new Inventory(new User());

        try
        {
            inventory.setAssociatedUser(testUser);
        }
        catch (InventoryAlreadyAssociatedException e)
        {
            exception = true;
        }

        Assert.isTrue(exception);
        Assert.isTrue(inventory.getAssociatedUser() != testUser);
    }
}
