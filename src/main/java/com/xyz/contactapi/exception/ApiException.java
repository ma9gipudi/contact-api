package com.xyz.contactapi.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
