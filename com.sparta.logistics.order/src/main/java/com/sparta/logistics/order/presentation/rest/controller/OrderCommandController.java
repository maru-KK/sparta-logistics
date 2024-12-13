package com.sparta.logistics.order.presentation.rest.controller;

import com.sparta.logistics.order.application.service.OrderService;
import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import com.sparta.logistics.order.presentation.rest.dto.commnad.OrderCreation.Request;
import com.sparta.logistics.order.presentation.rest.dto.commnad.OrderCreation.Response;
import com.sparta.logistics.order.presentation.rest.dto.security.Actor;
import com.sparta.logistics.order.presentation.rest.util.ApiResponse;
import com.sparta.logistics.order.presentation.rest.util.ApiResponse.Success;
import com.sparta.logistics.order.presentation.rest.util.actor.LoginActor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderCommandController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Success<Response>> createOrder(
        @Valid @RequestBody Request request,
        @LoginActor Actor actor
    ) {
        OrderForCreate orderForCreate = request.toDomain(actor.userId());
        Order order = orderService.createOrder(orderForCreate);

        Response response = Response.from(order);
        return ApiResponse.success(response, HttpStatus.CREATED);
    }
}
