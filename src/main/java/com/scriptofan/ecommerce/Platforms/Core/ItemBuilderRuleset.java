package com.scriptofan.ecommerce.Platforms.Core;

import com.scriptofan.ecommerce.LocalItem.LocalItem;

import java.util.Map;

public interface ItemBuilderRuleset {

    LocalItem apply(LocalItem localItem, final Map<String, String> fields);

}
