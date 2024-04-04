package com.jdbccrud.exception;

import com.jdbccrud.utility.LoggerUtil;
import jakarta.persistence.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final String errorResponse = "An internal error occurred. Please try again.";
    private final String loggerTypeError = "ERROR: ";
    private final String loggerTypeWarn = "WARN: ";



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

    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, IllegalArgumentException.class, EntityExistsException.class})
    public ResponseEntity<String> handleArgumentExceptionTypes(RuntimeException e){
        LoggerUtil.warn(loggerTypeWarn + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoResultException.class, NonUniqueResultException.class, EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleMethodArgumentNotValidException(RuntimeException e){
        LoggerUtil.warn(loggerTypeWarn + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({QueryTimeoutException.class, LockTimeoutException.class, OptimisticLockException.class})
    public ResponseEntity<Object> handleQueryExecutionExceptions(RuntimeException e) {
        LoggerUtil.warn(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TransactionRequiredException.class)
    public ResponseEntity<Object> handleTransactionRequiredException(TransactionRequiredException e) {
        LoggerUtil.warn(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Object> handlePersistenceExceptions(PersistenceException e) {
        LoggerUtil.warn(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GeneralCatchAllException.class)
    public ResponseEntity<String> handleGeneralExceptions(Exception e){
        LoggerUtil.error(loggerTypeError + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more exception handlers here for other types of exceptions.
}
