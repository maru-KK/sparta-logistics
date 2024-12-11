package com.sparta.logistics.product.presentation.rest.controller;

import com.sparta.logistics.product.application.service.ProductService;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.domain.ProductForUpdate;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductCreation;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductCreation.Response;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductModification;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductModification.Request;
import com.sparta.logistics.product.presentation.rest.dto.security.Actor;
import com.sparta.logistics.product.presentation.rest.dto.security.Role;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse.Success;
import com.sparta.logistics.product.presentation.rest.util.actor.LoginActor;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@RestController
public class ProductCommandController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Success<ProductCreation.Response>> createProduct(
        @RequestBody ProductCreation.Request request,
        @LoginActor Actor actor
    ) {
        if (!Objects.equals(actor.role(), Role.COMPANY)) {
            throw new IllegalArgumentException("비정상적인 접근");
        }

        ProductForCreate productForCreate = request.toDomain(actor.userId());
        Product product = productService.createProduct(productForCreate);

        Response response = Response.from(product);
        return ApiResponse.success(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Success<Response>> updateProduct(
        @PathVariable("productId") Long productId,
        @RequestBody Request request,
        @LoginActor Actor actor
    ) {
        // 권한 처리 로직 추가 예정
        if (!Objects.equals(request.getProductId(), productId) ||
            !Objects.equals(actor.role(), Role.COMPANY)) {

            System.out.println("productId = " + productId);
            System.out.println("request = " + request);
            System.out.println("actor = " + actor);
            throw new IllegalArgumentException("비정상적인 접근");
        }

        ProductForUpdate productForUpdate = request.toDomain();
        Product product = productService.updateProduct(productForUpdate, actor.userId());

        Response response = ProductModification.Response.from(product);
        return ApiResponse.success(response, HttpStatus.OK);
    }
}
