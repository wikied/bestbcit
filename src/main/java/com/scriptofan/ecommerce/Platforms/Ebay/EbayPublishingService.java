package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformPublishingService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class EbayPublishingService implements PlatformPublishingService {
    @Override
    public Offer publish(Map<String, String> fields, Offer offer) {
        throw new NotImplementedException();
    }
}
