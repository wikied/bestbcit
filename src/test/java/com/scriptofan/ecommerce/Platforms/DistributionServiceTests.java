package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Config;
import com.scriptofan.ecommerce.Exception.AlreadyInitializedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.ItemDistributor.DistributionService;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformPublishingService;

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
     * Distribute should create call getPlatformPublishingService for each Offer
     * contained.
     */
    @Test(expected = NullPointerException.class)
    public void shouldCallGetPlatformPublishingServiceForEachOffer() {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[0] = true;
                return null;
            }
        });
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[1] = true;
                return null;
            }
        });
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[2] = true;
                return null;
            }
        });

        distributionService.distribute(localItem);
        assert (methodWasCalled[0] == true);
        assert (methodWasCalled[1] == true);
        assert (methodWasCalled[2] == true);
    }


    /*
     * Distribute should call publish() for each returned platformPublishingService.
     */
    @Test
    public void distributeShouldCallPublishOncePerOffer() {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        methodWasCalled[0] = true;
                        return null;
                    }
                };
            }
        });
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        methodWasCalled[1] = true;
                        return null;
                    }
                };
            }
        });
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        methodWasCalled[2] = true;
                        return null;
                    }
                };
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
    public void distributeShouldReturnSameLocalItem() {
        LocalItem initialLocalItem = new LocalItem();
        LocalItem returnedLocalItem;
        returnedLocalItem = this.distributionService.distribute(initialLocalItem);
        assert (returnedLocalItem == initialLocalItem);
    }


    /*
     * Distribute should feed all of the current LocalItem's fields into publish()
     */
    @Test
    public void distributeShouldFeedAllFieldsToPublish() throws RulesetCollisionException {
        LocalItem localItem = new LocalItem();

        // Add test fields to the map
        for (Map.Entry<String, String> entry : testFields.entrySet()) {
            localItem.addField(entry.getKey(), entry.getValue());
        }

        // Inside the publish() method, test for each field
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        for (Map.Entry<String, String> entry : fields.entrySet()) {
                            assert (localItem.getField(entry.getKey()).equals(entry.getValue()));
                        }
                        return null;
                    }
                };
            }
        });

        this.distributionService.distribute(localItem);
    }


    /*
     * Distribute should apply only the current offer into any one publish() call
     */
    @Test
    public void distributeShouldFeedOnlyCurrentOfferIntoPublish() {
        LocalItem localItem = new LocalItem();
        final Offer[] offersList = new Offer[2];

        // Add two offers, which will each check their likeness in the publish() method
        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                offersList[0] = this;
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        assert (offer == offersList[0]);
                        return null;
                    }
                };
            }
        });

        localItem.addOffer(new Offer() {
            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                offersList[1] = this;
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(final Map<String, String> fields, Offer offer) {
                        assert (offer == offersList[1]);
                        return null;
                    }
                };
            }
        });

        this.distributionService.distribute(localItem);
    }



    /*
     * Distribute(LocalItem) should allow offers to be modified by the containing
     * PlatformDistributionService.
     */
    @Test
    public void distributeShouldLetOffersBeModified() {
        // Stub offer
        class DummyOffer implements Offer {
            public boolean wasModified = false;

            @Override
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    @Override
                    public Offer publish(Map<String, String> fields, Offer offer) {
                        ((DummyOffer) offer).wasModified = true;
                        return offer;
                    }
                };
            }
        }

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new DummyOffer());

        this.distributionService.distribute(localItem);
        assert(((DummyOffer) localItem.getOffers().toArray()[0]).wasModified == true);
    }



    /*
     * Distribute(List) should call Distribute(LocalItem) on all items in list
     */
    @Test
    public void distributeListShouldDistributeAllItems() {
        final int       numItems    = 10;
        final int[]     callCount   = {0};
        List<LocalItem> localItems  = new ArrayList<LocalItem>();

        // Create a bunch of local items, which will all increment callCount when
        // they have publish() called on their offer
        for (int i = 0; i < numItems; ++i) {
            LocalItem localItem = new LocalItem();
            localItem.addOffer(new Offer() {
                @Override
                public PlatformPublishingService getPlatformPublishingService() {
                     return new PlatformPublishingService() {
                        @Override
                        public Offer publish(Map<String, String> fields, Offer offer) {
                            callCount[0]++;
                            return offer;
                        }};
                }});

            localItems.add(localItem);
        }

        // Call distribute on list and check callCount
        this.distributionService.distribute(localItems);
        assert(callCount[0] == numItems);
    }
}
