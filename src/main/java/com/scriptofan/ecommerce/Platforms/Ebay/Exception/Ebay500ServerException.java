package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class Ebay500ServerException extends Exception {
    public Ebay500ServerException() {
    }

    public Ebay500ServerException(String message) {
        super(message);
    }

    public Ebay500ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public Ebay500ServerException(Throwable cause) {
        super(cause);
    }

    public Ebay500ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
