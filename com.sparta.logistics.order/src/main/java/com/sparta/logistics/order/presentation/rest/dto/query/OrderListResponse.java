package com.sparta.logistics.order.presentation.rest.dto.query;

import com.sparta.logistics.order.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderListResponse {

    private Long orderId;
    private Long supplyCompanyId;
    private Long consumeCompanyId;
    private Long productId;
    private Integer quantity;
    private String status;
    private Long orderedBy;

    public static OrderListResponse from(Order order) {
        return new OrderListResponse(
            order.getId(),
            order.getSupplyCompanyId(),
            order.getConsumeCompanyId(),
            order.getProductId(),
            order.getQuantity(),
            order.getStatus().name(),
            order.getOrderedBy()
        );
    }
}
