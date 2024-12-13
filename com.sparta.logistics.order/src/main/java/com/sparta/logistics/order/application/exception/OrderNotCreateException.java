package com.sparta.logistics.order.application.exception;

public class OrderNotCreateException extends RuntimeException {

    public OrderNotCreateException(String message) {
        super(message);
    }
}
