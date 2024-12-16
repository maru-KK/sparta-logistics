package com.sparta.logistics.delivery.infrastructure.external.product;

public interface ProductPort {
    ProductDetailResponse findOne(Long productId);
}
