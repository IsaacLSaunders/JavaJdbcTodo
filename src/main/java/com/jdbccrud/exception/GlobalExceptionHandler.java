package com.jdbccrud.exception;

import com.jdbccrud.utility.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String errorResponse = "An internal error occurred. Please try again.";
    private final String loggerTypeError = "ERROR: ";


    @ExceptionHandler(DatabaseAccessException.class)
    public ResponseEntity<String> handleDatabaseAccessException(DatabaseAccessException e) {
        // Log the exception details internally
        LoggerUtil.error(loggerTypeError + e );
        // Return a generic error response to the client
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e){
        LoggerUtil.error(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralCatchAllException.class)
    public ResponseEntity<String> handleGeneralExceptions(Exception e){
        LoggerUtil.error(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more exception handlers here for other types of exceptions.
}
