package com.scriptofan.ecommerce;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.LocalItem.ItemSyncService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.LocalItemFactory;
import com.scriptofan.ecommerce.Platforms.DistributionService;
import com.scriptofan.ecommerce.User.User;
import com.scriptofan.ecommerce.parsercsv.ParserCsvService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadSequenceIntegrationTests {

    private ParserCsvService    parserCsvService;
    private LocalItemFactory    localItemFactory;
    private ItemSyncService     itemSyncService;
    private DistributionService distributionService;

    @Before
    private void init() {
        this.parserCsvService       = new ParserCsvService();
        this.localItemFactory       = new LocalItemFactory();
        this.itemSyncService        = new ItemSyncService();
        this.distributionService    = new DistributionService();
    }

    @Test
    public void completeUploadIntegrationTest()
            throws AlreadyBoundException, RulesetCollisionException {

        File    csvFile = null;
        User    user    = new User();

        List<Map<String, String>>   parsedCsv   = null;
        List<LocalItem>             localItems  = null;

        // Parse CSV
        // Create local items from parsed CSV file
        localItems = this.localItemFactory.createLocalItems(parsedCsv);

        // Associate user with each local item
        for (LocalItem item : localItems) {
            item.associateUser(user);
        }
        // (Update local items based on db)
        localItems = itemSyncService.sync(localItems);

        // Distribute local items
        distributionService.distribute(localItems);

        // Sync items with DB
        localItems = itemSyncService.sync(localItems);

        // Generate page report
    }

}
