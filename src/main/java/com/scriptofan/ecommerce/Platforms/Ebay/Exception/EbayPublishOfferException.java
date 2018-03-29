package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class EbayPublishOfferException extends Exception {
    public EbayPublishOfferException() {
    }

    public EbayPublishOfferException(String message) {
        super(message);
    }

    public EbayPublishOfferException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbayPublishOfferException(Throwable cause) {
        super(cause);
    }

    public EbayPublishOfferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
