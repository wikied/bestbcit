package com.scriptofan.ecommerce.internal.user.Exception;

/**
 * This account is already associated with a user.
 */
public class AccountAlreadyAssociatedException extends Exception {

    public AccountAlreadyAssociatedException() {
    }

    public AccountAlreadyAssociatedException(String message) {
        super(message);
    }
}
