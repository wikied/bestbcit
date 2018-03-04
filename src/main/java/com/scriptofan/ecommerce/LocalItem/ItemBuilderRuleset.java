package com.scriptofan.ecommerce.LocalItem;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;

import java.util.Map;

public interface ItemBuilderRuleset  {

    LocalItem apply(LocalItem localItem, final Map<String, String> fields) throws
            RulesetCollisionException, RulesetViolationException, NotImplementedException;

}
