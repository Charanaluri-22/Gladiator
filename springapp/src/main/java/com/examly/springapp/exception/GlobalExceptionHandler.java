package com.examly.springapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Global exception handler to handle various exceptions across the entire application.
 * Uses @ControllerAdvice to apply global exception handling.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles EntityNotFoundException.
     *
     * @param e the EntityNotFoundException that was thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Handles EntityExistsException.
     *
     * @param e the EntityExistsException that was thrown.
     * @return a ResponseEntity with a 409 status and the exception message.
     */
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExistsException(EntityExistsException e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }
    
    /**
     * Handles EmptyCartException.
     *
     * @param e the EmptyCartException that was thrown.
     * @return a ResponseEntity with a 404 status and the exception message.
     */
    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<String> handleEmptyCartException(EmptyCartException e) {
        return ResponseEntity.status(204).body(e.getMessage());
    }

     /**
     * Handles NoContentFoundException.
     *
     * @param e the NoContentException that was thrown.
     * @return a ResponseEntity with a 204 status and the exception message.
     */
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<String> handleNoContentException(NoContentException e){
        return ResponseEntity.status(204).body(e.getMessage());
    }
}


