package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Platforms.Interface.Offer;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformPublishingService;

import java.util.Map;

public class EbayPublisherService implements PlatformPublishingService {
    @Override
    public Offer publish(Map<String, String> fields, Offer offer) throws NotImplementedException {
        throw new NotImplementedException();
    }
}
