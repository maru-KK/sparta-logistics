package com.sparta.logistics.delivery.presentation.util.exception;

public class DeliveryPersonNotFoundException extends RuntimeException {
    public DeliveryPersonNotFoundException(String message) {
        super(message);
    }
}