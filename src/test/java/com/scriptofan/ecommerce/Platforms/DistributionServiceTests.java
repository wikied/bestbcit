package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributionServiceTests {

    private Map<String, String> testFields;
    private DistributionService distributionService;

    @Before
    public void init() {
        try {
            Config.init();
        } catch (AlreadyInitializedException e) { /* catch error */ }

        this.distributionService = new DistributionService();

        this.testFields = new HashMap<>();
        testFields.put("key1", "value1");
        testFields.put("aaaa", "bbbbbb");
        testFields.put("123", "yo47");
        testFields.put("x", "3.14");
    }



    /*
     * Distribute should call post() for each returned platformPublishingService.
     */
    @Test
    public void distributeShouldCallPostOncePerOffer() throws NotImplementedException {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new Offer(localItem) {
            @Override
            public void post() {
                methodWasCalled[0] = true;
            }
        });
        localItem.addOffer(new Offer(localItem) {
            @Override
            public void post() {
                methodWasCalled[1] = true;
            }
        });
        localItem.addOffer(new Offer(localItem) {
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
        class DummyOffer extends Offer {
            public boolean wasModified = false;

            public DummyOffer(LocalItem localItem) {
                super(localItem);
            }

            @Override
            public void post() {
                wasModified = true;
            }
        }

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new DummyOffer(localItem));

        this.distributionService.distribute(localItem);
        assert(((DummyOffer) localItem.getOffers().toArray()[0]).wasModified == true);
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
            localItem.addOffer(new Offer(localItem) {
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
}
