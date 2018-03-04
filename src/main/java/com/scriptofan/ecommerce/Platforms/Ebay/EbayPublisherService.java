package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.Offer;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

public class EbayPublisherService implements PlatformPublishingService {
    @Override
    public LocalItem publish(Map<String, String> fields, Offer offer) {
        throw new NotImplementedException();
    }
}
