package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class BadEbayTokenException extends Exception {
    public BadEbayTokenException() {
    }

    public BadEbayTokenException(String message) {
        super(message);
    }

    public BadEbayTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadEbayTokenException(Throwable cause) {
        super(cause);
    }

    public BadEbayTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
