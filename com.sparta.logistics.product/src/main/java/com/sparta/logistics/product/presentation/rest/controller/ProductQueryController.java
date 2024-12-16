package com.sparta.logistics.product.presentation.rest.controller;

import static com.sparta.logistics.product.presentation.rest.exception.ErrorMessages.*;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.infrastructure.cache.adapter.ProductCacheAdapter;
import com.sparta.logistics.product.presentation.rest.dto.query.ProductDetailResponse;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse.Success;
import com.sparta.logistics.product.presentation.rest.util.search.SearchCondition;
import com.sparta.logistics.product.infrastructure.persistence.adapter.ProductQueryAdapter;
import com.sparta.logistics.product.infrastructure.persistence.search.ProductSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductQueryController {

    private final ProductQueryAdapter productQueryAdapter;
    private final ProductCacheAdapter productCacheAdapter;

    @GetMapping("/{id}")
    public ResponseEntity<Success<ProductDetailResponse>> findOne(
        @PathVariable("id") Long productId
    ) {
        Product product = productQueryAdapter.findById(productId).orElseThrow(() ->
            new IllegalArgumentException(String.format(PRODUCT_NOT_FOUND.getMessage(), productId)));
        productCacheAdapter.save(product);

        ProductDetailResponse response = ProductDetailResponse.from(product);

        return ApiResponse.success(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Success<Page<ProductDetailResponse>>> search(
        @SearchCondition ProductSearchCondition searchCondition
    ) {
        Page<ProductDetailResponse> response = productQueryAdapter.search(searchCondition)
            .map(ProductDetailResponse::from);

        return ApiResponse.success(response, HttpStatus.OK);
    }
}
