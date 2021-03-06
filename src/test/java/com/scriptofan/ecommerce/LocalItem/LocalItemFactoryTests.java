package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalItemFactoryTests {

    @Autowired
    private LocalItemFactory localItemFactory;

    @Autowired
    private Config config;

    @Before
    public void initTestSubjects() throws AlreadyRegisteredException, AlreadyBoundException {
        config.init();
    }



    /*
     * Passing null collection of maps should throw NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullFieldCollectionShouldThrowException() throws RulesetCollisionException, RulesetViolationException, NotImplementedException {
        localItemFactory.createLocalItems(null);
    }



    /*
     * Passing a list containing one or more null values should throw a NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void nullMapShouldThrowException() throws RulesetCollisionException, RulesetViolationException, NotImplementedException {
        List<Map<String, String>>   fieldCollection = new ArrayList<>();
        fieldCollection.add(null);
        localItemFactory.createLocalItems(fieldCollection);
    }



    /*
     * Passing a valid List<Map<String, String>> should return one LocalItem for each item in the map.
     */
    @Test
    public void validMapShouldReturnSameNumberOfLocalItems() throws RulesetCollisionException, RulesetViolationException, NotImplementedException {
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

            assert(returnedItems.size() == i + 1);

            for (LocalItem item : returnedItems) {
                assert (item != null);
            }
        }
    }


}
