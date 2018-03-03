package com.scriptofan.ecommerce.Platforms.Ebay;

import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Core.ItemBuilderRuleset;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class EbayItemBuilderRuleset implements ItemBuilderRuleset {

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields) {
        throw new NotImplementedException();
    }

    private void createInventoryItemRuleset(LocalItem localItem, Map<String, String> fields) {
        
    }


}
