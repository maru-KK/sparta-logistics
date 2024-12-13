package com.sparta.logistics.product.application.exception;

public class ProductCreateFailureException extends ProductLogicException{

    public ProductCreateFailureException(String message) {
        super(String.format(ErrorMessages.PRODUCT_CREATION_FAILURE.getMessage(), message));
    }
}
