package com.multigenesys.ecommerce.exception;

public class UserWithEmailNotExistException extends RuntimeException {
    public UserWithEmailNotExistException(String message) {
        super(message);
    }
}
