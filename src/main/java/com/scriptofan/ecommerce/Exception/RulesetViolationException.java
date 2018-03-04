package com.scriptofan.ecommerce.Exception;

public class RulesetViolationException extends Exception {

    public RulesetViolationException() {
    }

    public RulesetViolationException(String message) {
        super(message);
    }

    public RulesetViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RulesetViolationException(Throwable cause) {
        super(cause);
    }

    public RulesetViolationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
