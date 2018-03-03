package com.scriptofan.ecommerce.LocalItem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.utilities.Assert;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemLogTests {

    // A new ItemLog should not return any logs
    @Test
    public void newItemLogShouldBeBlank() {
        ItemLog         itemLog;
        List<String>    returnedLogs;

        itemLog         = new ItemLog();
        returnedLogs    = itemLog.getFullLog();

        assert(returnedLogs.size() == 0);
    }

    // Adding one itemLog should return that item
    // Adding multiple items to itemLog should return the same number of items, and they should be in the same order.

}
