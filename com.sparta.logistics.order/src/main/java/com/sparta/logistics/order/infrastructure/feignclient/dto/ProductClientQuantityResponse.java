package com.sparta.logistics.order.infrastructure.feignclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientQuantityResponse {

    private Long id;
    private String name;
    private Integer quantity;
}
