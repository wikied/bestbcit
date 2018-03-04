package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.Offer;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributionServiceTests {

    private DistributionService distributionService;

    @BeforeAll
    public void init() {
        this.distributionService = new DistributionService();
    }

    // Distribute should feed all of the current LocalItem's fields into publish()
    // Distribute should apply only the current offer into any one publish() call
    // Distribute should return the same LocalItem back


    /*
     * Distribute should create call getPlatformPublishingService for each Offer
     * contained.
     */
    @Test
    public void shouldCallGetPlatformPublishingServiceForEachOffer() {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[0] = true;
                return null; }});
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[1] = true;
                return null; }});
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                methodWasCalled[2] = true;
                return null; }});

        distributionService.distribute(localItem);
        assert(methodWasCalled[0] == true);
        assert(methodWasCalled[1] == true);
        assert(methodWasCalled[2] == true);
    }




    /*
     * Distribute should call publish() for each returned platformPublishingService.
     */
    @Test
    public void distributeShouldCallPublishOncePerOffer() {
        final boolean[] methodWasCalled = {false, false, false};

        LocalItem localItem = new LocalItem();
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    public LocalItem publish(LocalItem localItem) {
                        methodWasCalled[0] = true;
                        return null;
                    }};
            }});
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    public LocalItem publish(LocalItem localItem) {
                        methodWasCalled[1] = true;
                        return null;
                    }};
            }});
        localItem.addOffer(new Offer() {
            public PlatformPublishingService getPlatformPublishingService() {
                return new PlatformPublishingService() {
                    public LocalItem publish(LocalItem localItem) {
                        methodWasCalled[2] = true;
                        return null;
                    }};
            }});

        distributionService.distribute(localItem);
        assert(methodWasCalled[0] == true);
        assert(methodWasCalled[1] == true);
        assert(methodWasCalled[2] == true);
    }

}
