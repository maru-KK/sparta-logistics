package com.sparta.logistics.delivery.infrastructure.external.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {
    private final ProductFeignClient productFeignClient;

    @Override
    public ProductDetailResponse findOne(Long productId) {
         return productFeignClient.findOne(productId).getBody().getData();
    }
}
