package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.DistributionCalculator;
import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.LocalOffer;
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
public class DistributionServiceTests {

    private Map<String, String> testFields;

    @Autowired
    private DistributionService distributionService;

    @Autowired
    private PlatformRegistry platformRegistry;

    @Autowired
    private Config config;


    @Before
    public void init() throws AlreadyBoundException, AlreadyRegisteredException {
        config.init();

        this.testFields = new HashMap<>();
        testFields.put("key1", "value1");
        testFields.put("aaaa", "bbbbbb");
        testFields.put("123", "yo47");
        testFields.put("x", "3.14");

        if (platformRegistry.getQuantityDistributionScheme() == null) {
            platformRegistry.setQuantityDistributionScheme(new DistributionCalculator());
        }
    }



    /*
     * Distribute should call post() for each returned platformPublishingService.
     */
    @Test
    public void distributeShouldCallPostOncePerOffer() throws NotImplementedException {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new LocalOffer(localItem) {
            @Override
            public void post() {
                methodWasCalled[0] = true;
            }
        });
        localItem.addOffer(new LocalOffer(localItem) {
            @Override
            public void post() {
                methodWasCalled[1] = true;
            }
        });
        localItem.addOffer(new LocalOffer(localItem) {
            @Override
            public void post() {
                methodWasCalled[2] = true;
            }
        });

        distributionService.distribute(localItem);
        assert (methodWasCalled[0] == true);
        assert (methodWasCalled[1] == true);
        assert (methodWasCalled[2] == true);
    }


    /*
     * Distribute should return the same LocalItem back.
     */
    @Test
    public void distributeShouldReturnSameLocalItem() throws NotImplementedException {
        LocalItem initialLocalItem = new LocalItem();
        LocalItem returnedLocalItem;
        returnedLocalItem = this.distributionService.distribute(initialLocalItem);
        assert (returnedLocalItem == initialLocalItem);
    }



    /*
     * Distribute(LocalItem) should allow offers to be modified by the containing
     * PlatformDistributionService.
     */
    @Test
    public void distributeShouldLetOffersBeModified() throws NotImplementedException {
        // Stub offer
        class DummyLocalOffer extends LocalOffer {
            public boolean wasModified = false;

            public DummyLocalOffer(LocalItem localItem) {
                super(localItem);
            }

            @Override
            public void post() {
                wasModified = true;
            }
        }

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new DummyLocalOffer(localItem));

        this.distributionService.distribute(localItem);
        assert(((DummyLocalOffer) localItem.getLocalOffers().toArray()[0]).wasModified == true);
    }



    /*
     * Distribute(List) should call Distribute(LocalItem) on all items in list
     */
    @Test
    public void distributeListShouldDistributeAllItems() throws NotImplementedException {
        final int       numItems    = 10;
        final int[]     callCount   = {0};
        List<LocalItem> localItems  = new ArrayList<>();

        // Create a bunch of local items, which will all increment callCount when
        // they have publish() called on their offer
        for (int i = 0; i < numItems; ++i) {
            LocalItem localItem = new LocalItem();
            localItem.addOffer(new LocalOffer(localItem) {
                @Override
                public void post() {
                    callCount[0]++;
                }
            });

            localItems.add(localItem);
        }

        // Call distribute on list and check callCount
        this.distributionService.distribute(localItems);
        assert(callCount[0] == numItems);
    }



    @Test
    public void sumOfOfferQtyShouldEqualTotalQty() throws NotImplementedException {
        int         quantity;
        LocalItem   item;

        item = new LocalItem();
        item.setTotalQuantity(23);
        item.addOffer(new LocalOffer(item) {
            public void post() { }
        });
        item.addOffer(new LocalOffer(item) {
            public void post() { }
        });

        distributionService.distribute(item);

        quantity = 0;
        for (LocalOffer localOffer : item.getLocalOffers()) {
            quantity += localOffer.getQuantity();
        }
        assert(quantity == item.getTotalQuantity());
    }
}
