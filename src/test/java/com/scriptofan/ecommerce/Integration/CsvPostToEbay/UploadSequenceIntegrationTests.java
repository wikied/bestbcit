package com.scriptofan.ecommerce.Integration.CsvPostToEbay;

import com.scriptofan.ecommerce.CSVParser.ParserCsvService;
import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.ItemSyncService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.LocalItemFactory;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
import com.scriptofan.ecommerce.Platforms.PlatformRegistry;
import com.scriptofan.ecommerce.User.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadSequenceIntegrationTests {

    public static final String TEST_CSV_DIRECTORY = "./src/test/java/com/scriptofan/ecommerce/Integration/CsvPostToEbay/";

    @Autowired
    private ParserCsvService    parserCsvService;

    @Autowired
    private LocalItemFactory    localItemFactory;

    @Autowired
    private ItemSyncService     itemSyncService;

    @Autowired
    private DistributionService distributionService;

    @Autowired
    private PlatformRegistry platformRegistry;

    @Autowired
    private Config config;

    @Before
    public void init() {
        try {
            config.init();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void completeUploadIntegrationTest()
            throws  AlreadyBoundException,
                    RulesetCollisionException,
                    RulesetViolationException,
                    NotImplementedException,
                    IOException
    {

        String                      filename;
        File                        csvFile;
        User                        user;
        List<Map<String, String>>   rawParsedItems;
        List<LocalItem>             localItems;


        filename        = TEST_CSV_DIRECTORY + "test_02.csv";
        csvFile         = new File(filename);
        assert(csvFile != null);

        //Included the multipartFile parse
        rawParsedItems  = parserCsvService.parseCsv(csvFile);
        assert(rawParsedItems != null);

        localItems      = this.localItemFactory.createLocalItems(rawParsedItems);

        user = new User();
        for (LocalItem item : localItems) {
            item.associateUser(user);
            assert(item.getUser() != null);
        }

        localItems = itemSyncService.sync(localItems);
        localItems = distributionService.distribute(localItems);
        localItems = itemSyncService.sync(localItems);

        for (LocalItem item : localItems) {
            System.err.println("ITEM: " + item);
            for (String log : item.getFullLog()) {
                System.err.println(log);
            }
            System.err.println();

            for (LocalOffer offer : item.getLocalOffers()) {
                System.err.println("OFFER: " + offer);
                for (String log : offer.getFullLog()) {
                    System.err.println(log);
                }
                System.err.println();
            }
            System.err.println();
        }
    }

}
