package com.jdbccrud.exception;

public class DatabaseAccessException extends RuntimeException {
    public DatabaseAccessException(String message) {
        super(message);
    }

    public DatabaseAccessException(Throwable cause) {
        super(cause);
    }

    public DatabaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
