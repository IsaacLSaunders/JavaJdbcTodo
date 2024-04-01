package com.jdbccrud.exception;

public class GeneralCatchAllException extends RuntimeException {
    public GeneralCatchAllException(String message) {
        super(message);
    }

    public GeneralCatchAllException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralCatchAllException(Throwable cause) {
        super(cause);
    }
}
