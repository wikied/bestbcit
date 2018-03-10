package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.User.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.channels.NotYetBoundException;
import java.rmi.AlreadyBoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalItemTest {
    private LocalItem localItem;

    @Autowired
    private Config config;

    @Before
    public void init() throws AlreadyRegisteredException {
        config.init();
    }

    //Should throw NullPointerException due to passed null parameter.
    @Test(expected = NullPointerException.class)
    public void shouldCatchNullKeyAndValue() throws RulesetCollisionException {
        localItem = new LocalItem();
        String key = null;
        String value = null;
        localItem.addField(key, value);
    }

    //Should throw RulesetCollisionException for already existing key and value.
    @Test(expected = RulesetCollisionException.class)
    public void shouldCatchCollision() throws RulesetCollisionException{
        localItem = new LocalItem();
        String key = "samekey";
        String value1 = "value1";
        String value2 = "value2";
        localItem.addField(key, value1);
        localItem.addField(key, value2);
    }

    //Should throw NullPointerException for a null field.
    @Test(expected = NullPointerException.class)
    public void shouldCatchNullValueFromKey() {
        localItem = new LocalItem();
        String key = "TestKey";
        localItem.getField(key);
    }

    //Should successfully return true for the proper field associated with the key
    @Test
    public void shouldPairCorrectFieldWithKey() throws RulesetCollisionException{
        localItem = new LocalItem();
        String key = "TestKey";
        String expectedValue = "TestValue";
        localItem.addField(key, expectedValue);
        String actualValue = localItem.getField(key);
        assert(expectedValue.equals(actualValue));
    }

    //Offers should be size 0 as nothing was set
    @Test
    public void shouldCatchNoOffers(){
        localItem = new LocalItem();
        assert(localItem.getOffers().size() == 0);
    }

    //Should throw AlreadyBoundException for an already associated User.
    @Test(expected = AlreadyBoundException.class)
    public void shouldCatchAlreadyBound() throws AlreadyBoundException{
        localItem = new LocalItem();
        User testuser = new User("1");
        User testuser2 = new User("2");
        localItem.associateUser(testuser);
        localItem.associateUser(testuser2);
    }

    //Should throw NotYetBoundException for no user associated with localitem.
    @Test(expected = NotYetBoundException.class)
    public void shouldCatchNoUserAssociated() throws NotYetBoundException{
        localItem = new LocalItem();
        localItem.getUser();
    }

    //Should throw IllegalArgumentException for a negative value in the quantity field.
    @Test(expected = IllegalArgumentException.class)
    public void shouldCatchNegativeNumbers() throws IllegalArgumentException{
        localItem = new LocalItem();
        localItem.setTotalQuantity(-1);
    }

    //Should successfully return true for a valid quantity
    @Test
    public void shouldReturnValidQuantity(){
        localItem = new LocalItem();
        int expectedValue = 10;
        localItem.setTotalQuantity(expectedValue);
        int actualValue = localItem.getTotalQuantity();
        assert(expectedValue == actualValue);
    }

    //Sequence of logs should match
    @Test
    public void shouldContainLogs(){
        localItem = new LocalItem();
        localItem.log("log1");
        localItem.log("log2");
        localItem.log("log3");
        assert(localItem.getFullLog().size() == 3);
    }

    //Retrieving all fields and modifying one should not affect original
    @Test
    public void getAllFieldsShouldReturnCopy() throws RulesetCollisionException {
        localItem = new LocalItem();
        localItem.addField("key1", "value1");
        localItem.getAllFields().put("key1", "modifiedValue");

        assert(localItem.getField("key1").equals("value1"));
    }
}
