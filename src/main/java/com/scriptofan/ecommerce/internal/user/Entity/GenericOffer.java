package com.scriptofan.ecommerce.internal.user.Entity;

/**
 * Represents an offer on some third party retail platform.
 *
 * @author  Patrick Charles-Lundaahl
 * @version 1.0
 */
public abstract class GenericOffer {

    // The ID that is associated with a given external platform.
    private String platformAccountId;

    /**
     * Default constructor.
     *
     * @param platformAccountId PlatformAccountId
     */
    public GenericOffer(String platformAccountId) {
        this.platformAccountId = platformAccountId;
    }

    /**
     * Returns the Platform Account Id associated with this offer.
     *
     * @return Platform Account Id associated with this offer.
     */
    public final String getPlatformAccountId() {
        return platformAccountId;
    }
}
