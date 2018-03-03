package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import com.scriptofan.ecommerce.Reporting.LocalItemReport;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class EbayPublisherService implements PlatformPublishingService {
    @Override
    public LocalItemReport publish(LocalItem localItem) {
        throw new NotImplementedException();
    }

    @Override
    public List<LocalItemReport> publish(List<LocalItem> localItemList) {
        throw new NotImplementedException();
    }
}
