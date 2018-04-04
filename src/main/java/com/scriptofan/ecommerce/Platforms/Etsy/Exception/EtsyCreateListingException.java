package com.scriptofan.ecommerce.Platforms.Etsy.Exception;

public class EtsyCreateListingException extends Exception {
    public EtsyCreateListingException() {
    }

    public EtsyCreateListingException(String message) {
        super(message);
    }

    public EtsyCreateListingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtsyCreateListingException(Throwable cause) {
        super(cause);
    }

    public EtsyCreateListingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
