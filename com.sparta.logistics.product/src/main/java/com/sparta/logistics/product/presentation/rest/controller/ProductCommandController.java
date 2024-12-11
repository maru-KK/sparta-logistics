package com.sparta.logistics.product.presentation.rest.controller;

import com.sparta.logistics.product.application.service.ProductService;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductCreation;
import com.sparta.logistics.product.presentation.rest.dto.command.ProductCreation.Response;
import com.sparta.logistics.product.presentation.rest.dto.security.Actor;
import com.sparta.logistics.product.presentation.rest.util.ApiResponse;
import com.sparta.logistics.product.presentation.rest.util.actor.LoginActor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createProduct(
        @RequestBody ProductCreation.Request request,
        @LoginActor Actor actor
    ) {
        ProductForCreate productForCreate = request.toDomain(actor.userId());
        Product product = productService.createProduct(productForCreate);

        Response response = Response.from(product);

        return ApiResponse.success(response, HttpStatus.CREATED);
    }
}
