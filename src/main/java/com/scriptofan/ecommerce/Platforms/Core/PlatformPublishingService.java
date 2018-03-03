package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Reporting.LocalItemReport;

import java.util.List;

public interface PlatformPublishingService {

    LocalItemReport         publish(LocalItem localItem);
    List<LocalItemReport>   publish(List<LocalItem> localItemList);

}