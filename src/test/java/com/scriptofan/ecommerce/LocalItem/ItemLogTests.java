package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemLogTests {

    @Autowired
    private Config config;

    ItemLog         itemLog;
    List<String>    returnedLogs;

    /*
     * Initialize the ItemLog object.
     */
    @Before
    public void initializeItemLog() throws AlreadyRegisteredException {
        try {
            config.init();
        } catch (AlreadyInitializedException e) { /* catch error */ }
        itemLog         = new ItemLog();
    }



    /*
     * A new ItemLog should not return any logs
     */
    @Test
    public void newItemLogShouldBeBlank() {
        assert(itemLog.getFullLog().size() == 0);
    }



    /*
     * Number of log entries retrieved should equal the number of entries added.
     */
    @Test
    public void logsEnteredShouldEqualNumberOfLogsOut() {
        for (int i = 1; i <= 10; ++i) {
            itemLog.add("actual contents not checked here");
            assert(itemLog.getFullLog().size() == i);
        }
    }



    /*
     * Logs retrieved should equal the valid logs entered in terms of content and order.
     */
    @Test
    public void logsEnteredShouldEqualLogsRetrieved() {
        String[] logData = {
                "log 1",
                "log 2",
                "log 3",
                "log 4",
                "log 5",
                "log 6"
        };

        for (int i = 0; i < logData.length; ++i) {
            itemLog.add(logData[i]);

            returnedLogs = itemLog.getFullLog();
            for (int j = 0; j < returnedLogs.size(); ++j) {
                assert(returnedLogs.get(j).equals(logData[j]));
            }
        }
    }




    /*
     * When null values are entered and retrieved, ItemLog should return a placeholder message.
     */
    @Test
    public void nullMessagesStringsShouldReturnIndicator() {
        itemLog.add(null);
        returnedLogs = itemLog.getFullLog();
        assert(returnedLogs.get(0).equals(ItemLog.NULL_MESSAGE));
    }

}
