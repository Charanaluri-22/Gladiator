package com.examly.springapp.exception;

/**
 * Custom exception to handle scenarios where the shopping cart is empty.
 * 
 * Extends `RuntimeException` to allow unchecked exceptions.
 */
public class EmptyCartException extends RuntimeException {

    /**
     * Default constructor for `EmptyCartException`.
     * Initializes the exception with no message.
     */
    public EmptyCartException() {
        super();
    }

    /**
     * Constructor for `EmptyCartException`.
     *
     * @param msg the exception message describing the error.
     */
    public EmptyCartException(String msg) {
        super(msg);
    }
}