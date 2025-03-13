package com.examly.springapp.exception;

/**
 * Custom exception to handle scenarios where the shopping cart is empty.
 * 
 * Extends `RuntimeException` to allow unchecked exceptions.
 */

public class NoContentException extends RuntimeException {
    /**
     * Default constructor for `NoContentException`.
     * Initializes the exception with no message.
     */
    public NoContentException() {
        super();
    }

    /**
     * Constructor for `NoContentException`.
     *
     * @param msg the exception message describing the error.
     */
    public NoContentException(String msg) {
        super(msg);
    }

}
