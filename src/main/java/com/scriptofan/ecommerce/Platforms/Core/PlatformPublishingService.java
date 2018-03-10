package com.scriptofan.ecommerce.Platforms.Core;


import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;

import java.util.List;

public interface PlatformPublishingService {

    LocalItem         publish(LocalItem localItem) throws NotImplementedException;
    List<LocalItem>   publish(List<LocalItem> localItemList) throws NotImplementedException;

}