package com.scriptofan.ecommerce.Platforms.Interface;

import java.util.Map;

public interface PlatformPublishingService {

    Offer publish(final Map<String, String> fields, Offer offer);

}