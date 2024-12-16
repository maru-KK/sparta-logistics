package com.sparta.logistics.delivery.infrastructure.external.product;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.AuthApiResponse;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductFeignClient {
    @GetMapping("/api/v1/products/{id}")
    ResponseEntity<AuthApiResponse.Success<ProductDetailResponse>> findOne(@PathVariable("id") Long productId);
}
