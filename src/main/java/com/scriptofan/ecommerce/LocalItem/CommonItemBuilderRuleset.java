package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Exception.NotImplementedException;


import java.util.Map;

/**
 * Responsible for populating a LocalItem with all fields that are
 * required on an application-wide basis.
 */
public class CommonItemBuilderRuleset implements ItemBuilderRuleset {



    /**
     * Populates a LocalItem with any fields that are used application-wide.
     *
     * @param localItem LocalItem being populated.
     * @param fields a collection of fields from which values will be taken.
     * @return the LocalItem that was passed in.
     * @throws NotImplementedException
     */
    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields) {
        return localItem;
    }
}
