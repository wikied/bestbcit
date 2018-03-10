package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalItemFactoryTests {

    @Autowired
    private LocalItemFactory localItemFactory;

    @Before
    public void initTestSubjects() {
        try {
            Config.init();
        } catch (AlreadyInitializedException e) { /* catch error */ }
    }



    /*
     * Passing null collection of maps should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullFieldCollectionShouldThrowException() throws RulesetCollisionException, RulesetViolationException {
        localItemFactory.createLocalItems(null);
    }



    /*
     * Passing a list containing one or more null values should throw a NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullMapShouldThrowException() throws RulesetCollisionException, RulesetViolationException {
        List<Map<String, String>>   fieldCollection = new ArrayList<>();
        fieldCollection.add(null);
        localItemFactory.createLocalItems(fieldCollection);
    }



    /*
     * Passing a valid List<Map<String, String>> should return one LocalItem for each item in the map.
     */
    @Test
    public void validMapShouldReturnSameNumberOfLocalItems() throws RulesetCollisionException, RulesetViolationException {
        Map<String, String>         fieldMap;
        List<Map<String, String>>   fieldCollection;
        List<LocalItem>             returnedItems;

        fieldCollection = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            returnedItems = new ArrayList<>();
            fieldMap = new HashMap<>();
            fieldMap.put("a" + i, "a" + i);
            fieldCollection.add(fieldMap);

            returnedItems = localItemFactory.createLocalItems(fieldCollection);

            Assert.that(returnedItems.size() == i + 1, "number of items = " + returnedItems.size());

            for (LocalItem item : returnedItems) {
                assert (item != null);
            }
        }
    }


}
