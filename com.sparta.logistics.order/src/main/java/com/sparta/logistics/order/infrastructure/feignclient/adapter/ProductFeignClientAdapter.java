package com.sparta.logistics.order.infrastructure.feignclient.adapter;

import com.sparta.logistics.order.application.outputport.ProductOutputPort;
import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.infrastructure.feignclient.client.ProductClient;
import com.sparta.logistics.order.infrastructure.feignclient.dto.ProductClientResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductFeignClientAdapter implements ProductOutputPort {

    private final ProductClient productClient;

    @CircuitBreaker(name = "order-service", fallbackMethod = "fallbackFindOne")
    @Override
    public Product findOne(Long productId) {
        ProductClientResponse product = productClient.getProduct(productId).getBody().getData();
        return product.toDomain();
    }

    public Product fallbackFindOne(Long productId, Throwable throwable) {
        log.error("fallback OrderFeignClient.findOne =", throwable);
        return Product.ERROR_PRODUCT;
    }
}
