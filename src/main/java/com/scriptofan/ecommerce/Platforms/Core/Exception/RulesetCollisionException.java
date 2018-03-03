package com.scriptofan.ecommerce.Platforms.Core.Exception;

public class RulesetCollisionException extends Exception {

    public RulesetCollisionException() {
    }

    public RulesetCollisionException(String message) {
        super(message);
    }

    public RulesetCollisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RulesetCollisionException(Throwable cause) {
        super(cause);
    }

    public RulesetCollisionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
