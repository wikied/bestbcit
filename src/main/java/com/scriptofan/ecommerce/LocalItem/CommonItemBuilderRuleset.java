package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Platforms.Core.ItemBuilderRuleset;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class CommonItemBuilderRuleset implements ItemBuilderRuleset {

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields) {
        throw new NotImplementedException();
    }
}
