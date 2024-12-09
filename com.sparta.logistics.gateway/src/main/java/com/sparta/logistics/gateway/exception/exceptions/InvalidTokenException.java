package com.sparta.logistics.gateway.exception.exceptions;

public class InvalidTokenException extends UnAuthorizedException {

    public InvalidTokenException(String message) {
        super(message);
    }
}
