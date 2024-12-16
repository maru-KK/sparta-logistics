package com.sparta.logistics.order.infrastructure.feignclient.dto;

import com.sparta.logistics.order.domain.order.vo.ProductForChangeQuantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductClientQuantityRequest {

    private Long productId;
    private Integer changedCount;
    private Boolean isDecrease;
    private Long userId;

    public static ProductClientQuantityRequest from(ProductForChangeQuantity product) {
        return new ProductClientQuantityRequest(
            product.getProductId(),
            product.getChangedCount(),
            product.isDecrease(),
            product.getChangedBy()
        );
    }
}
