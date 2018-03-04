package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.LocalItem.Offer;

import java.util.List;
import java.util.Map;

public interface PlatformPublishingService {

    Offer publish(final Map<String, String> fields, Offer offer);

}