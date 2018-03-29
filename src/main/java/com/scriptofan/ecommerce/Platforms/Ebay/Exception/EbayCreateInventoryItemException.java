package com.scriptofan.ecommerce.Platforms.Ebay.Exception;

public class EbayCreateInventoryItemException extends Exception {

    public EbayCreateInventoryItemException() {
    }

    public EbayCreateInventoryItemException(String message) {
        super(message);
    }

    public EbayCreateInventoryItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public EbayCreateInventoryItemException(Throwable cause) {
        super(cause);
    }

    public EbayCreateInventoryItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
