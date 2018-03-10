package com.scriptofan.ecommerce.Platforms.Ebay;


import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Core.PlatformPublishingService;
import com.scriptofan.ecommerce.Exception.NotImplementedException;


import java.util.List;

public class EbayPublisherService implements PlatformPublishingService {
    @Override
    public LocalItem publish(LocalItem localItem) throws NotImplementedException{
        throw new NotImplementedException();
    }

    @Override
    public List<LocalItem> publish(List<LocalItem> localItemList) throws NotImplementedException{
        throw new NotImplementedException();
    }
}
