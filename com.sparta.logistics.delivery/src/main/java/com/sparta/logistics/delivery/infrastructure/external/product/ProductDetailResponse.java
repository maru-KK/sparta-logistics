package com.sparta.logistics.delivery.infrastructure.external.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {
    private Long id;
    private Long hubId;
    private Long companyId;
    private Integer quantity;
    private String name;
}
