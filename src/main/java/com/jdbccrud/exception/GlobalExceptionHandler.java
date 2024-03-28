package com.jdbccrud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{
    public GlobalExceptionHandler() {
    }

    public GlobalExceptionHandler(String message) {
        super(message);
    }

    public GlobalExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalExceptionHandler(Throwable cause) {
        super(cause);
    }
}
