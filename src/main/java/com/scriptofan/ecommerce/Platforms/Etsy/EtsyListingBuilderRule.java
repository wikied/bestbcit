package com.scriptofan.ecommerce.Platforms.Etsy;

import com.scriptofan.ecommerce.Exception.RulesetCollisionException;
import com.scriptofan.ecommerce.Exception.RulesetViolationException;

import java.util.Map;

public class EtsyListingBuilderRule {
    private final boolean   mustExist;
    private final String    keyInternal;
    private final String    keyOnEtsy;

    EtsyListingBuilderRule(String keyInternal, String keyOnEtsy, boolean mustExist) {
        this.mustExist   = mustExist;
        this.keyInternal = keyInternal;
        this.keyOnEtsy   = keyOnEtsy;
    }


    /**
     * Populates the passed-in localItem with a validated value from the set of fields.
     *
     * @param fields
     * @throws RulesetViolationException
     * @throws RulesetCollisionException
     */
    public String retrieveForStorage(final Map<String, String> fields)
            throws RulesetViolationException, RulesetCollisionException
    {
        String value = fields.get(keyInternal);
        validate(value);
        value = transform(value);
        return value;
    }


    /**
     * Validates the passed value.
     * @param value
     * @return
     * @throws RulesetViolationException
     */
    public boolean validate(String value) throws RulesetViolationException
    {
        if (mustExist && value == null) {
            throw new RulesetViolationException(keyInternal + " must not be null");
        }
        return true;
    }


    /**
     * Hook method. Override this to perform any desired transformations on the
     * passed value.
     *
     * @param value
     * @return
     */
    protected String transform(String value) {
        return value;
    }


    public final boolean mustExist() {
        return mustExist;
    }

    public final String getKeyInternal() {
        return keyInternal;
    }

    public final String getKeyOnEtsy() {
        return keyOnEtsy;
    }

}
