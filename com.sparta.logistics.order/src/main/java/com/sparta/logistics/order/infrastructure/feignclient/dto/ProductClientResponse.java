package com.sparta.logistics.order.infrastructure.feignclient.dto;

import com.sparta.logistics.order.domain.order.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientResponse {

    private Long id;
    private Long hubId;
    private Long companyId;
    private Integer quantity;
    private String name;

    public Product toDomain() {
        return new Product(id, name, quantity, companyId, hubId);
    }
}
