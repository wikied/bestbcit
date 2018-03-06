package com.scriptofan.ecommerce.Platforms.Interface;

import com.scriptofan.ecommerce.LocalItem.Offer;

import java.util.Map;

public interface PlatformPublishingService {

    Offer publish(final Map<String, String> fields, Offer offer);

}