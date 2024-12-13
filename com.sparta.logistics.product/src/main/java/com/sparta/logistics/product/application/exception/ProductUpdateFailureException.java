package com.sparta.logistics.product.application.exception;

public class ProductUpdateFailureException extends ProductLogicException{

    public ProductUpdateFailureException(String message) {
        super(String.format(ErrorMessages.PRODUCT_MODIFICATION_FAILURE.getMessage(), message));
    }
}
