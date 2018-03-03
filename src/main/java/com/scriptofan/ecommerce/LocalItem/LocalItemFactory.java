package com.scriptofan.ecommerce.LocalItem;

import com.scriptofan.ecommerce.User.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

public class LocalItemFactory {

    /*
     * For each Map in the list of maps, this needs to:
     *
     * 1) Create a new LocalItem
     * 2) Validate all the fields in the associated Map
     * 3) Use those fields to populate the fields in the LocalItem
     * 4) Create the LocalItem's Offer placeholders
     * 5) Set the initial quantity
     */
    public List<LocalItem> getNewLocalItem(List<Map<String, String>> Items, User user) {
        throw new NotImplementedException();
    }
}
