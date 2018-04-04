package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class OfferAlreadyExistsException extends Exception {

    public OfferAlreadyExistsException() {
    }

    public OfferAlreadyExistsException(String message) {
        super(message);
    }

    public OfferAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfferAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public OfferAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
