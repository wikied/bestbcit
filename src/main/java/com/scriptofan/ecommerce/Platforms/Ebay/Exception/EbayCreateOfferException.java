package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class EbayCreateOfferException extends Exception {
    public EbayCreateOfferException() {
    }

    public EbayCreateOfferException(String message) {
        super(message);
    }

    public EbayCreateOfferException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbayCreateOfferException(Throwable cause) {
        super(cause);
    }

    public EbayCreateOfferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
