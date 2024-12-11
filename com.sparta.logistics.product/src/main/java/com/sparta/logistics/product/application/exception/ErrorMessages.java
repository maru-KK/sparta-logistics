package com.sparta.logistics.product.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    PRODUCT_CREATION_FAILURE("상품 생성 실패: %s"),
    PRODUCT_MODIFICATION_FAILURE("상품 수정 실패: %s");

    private final String message;
}
