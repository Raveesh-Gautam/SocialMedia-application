package com.social.exception;

public class UserException extends RuntimeException {

    // Default constructor
    public UserException() {
        super();
    }

    // Constructor with custom message
    public UserException(String message) {
        super(message);
    }

    // Constructor with custom message and cause
    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with cause (for chaining exceptions)
    public UserException(Throwable cause) {
        super(cause);
    }
}
