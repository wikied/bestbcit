package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.NotImplementedException;
import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;
import com.scriptofan.ecommerce.LocalItem.LocalItem;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;

import java.util.Map;

public class EtsyItemBuilderRuleset implements ItemBuilderRuleset {

    private final String validFormat = "FIXED_PRICE";

    private boolean validCondition = false;

    private boolean validMarketPlaceId = false;

    @Override
    public LocalItem apply(LocalItem localItem, Map<String, String> fields)
            throws
            RulesetCollisionException,
            RulesetViolationException,
            NotImplementedException {
        return null;
    }






}
