package com.sparta.logistics.product.presentation.rest.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessages {

    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다. id=%s");

    private final String message;
}
