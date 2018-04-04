package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class ListingAlreadyExistsException extends Exception {
    public ListingAlreadyExistsException() {
    }

    public ListingAlreadyExistsException(String message) {
        super(message);
    }

    public ListingAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListingAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ListingAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
