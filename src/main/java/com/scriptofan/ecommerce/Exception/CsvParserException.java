package com.scriptofan.ecommerce.Exception;

public class CsvParserException extends Exception {
    public CsvParserException() {
    }

    public CsvParserException(String message) {
        super(message);
    }

    public CsvParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvParserException(Throwable cause) {
        super(cause);
    }

    public CsvParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
