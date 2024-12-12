package com.sparta.logistics.order.presentation.rest.controller;

import com.sparta.logistics.order.infrastructure.persistence.adapter.OrderQueryAdapter;
import com.sparta.logistics.order.infrastructure.persistence.search.OrderSearchCondition;
import com.sparta.logistics.order.presentation.rest.dto.query.OrderListResponse;
import com.sparta.logistics.order.presentation.rest.dto.security.Actor;
import com.sparta.logistics.order.presentation.rest.util.ApiResponse;
import com.sparta.logistics.order.presentation.rest.util.ApiResponse.Success;
import com.sparta.logistics.order.presentation.rest.util.actor.LoginActor;
import com.sparta.logistics.order.presentation.rest.util.search.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderQueryController {

    private final OrderQueryAdapter orderQueryAdapter;

    @GetMapping
    public ResponseEntity<Success<Page<OrderListResponse>>> search(
        @SearchCondition OrderSearchCondition searchCondition,
        @LoginActor Actor actor
    ) {
        Page<OrderListResponse> response = orderQueryAdapter.search(searchCondition, actor.userId())
            .map(OrderListResponse::from);

        return ApiResponse.success(response, HttpStatus.OK);
    }
}
