package com.sparta.logistics.order.domain.order;

import com.sparta.logistics.order.domain.order.vo.OrderStatus;
import java.time.LocalDateTime;

public class OrderForCreate {

    private Long orderedBy;
    private Long orderedCompany;

    private Long productId;
    private Integer orderedQuantity;

    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;
    private OrderStatus status;

    public OrderForCreate(
        Long orderedBy, Long orderedCompany,
        Long productId, Integer orderedQuantity,
        String requestMessage, LocalDateTime deliveryLimitedAt
    ) {
        this.orderedBy = orderedBy;
        this.orderedCompany = orderedCompany;
        this.productId = productId;
        this.orderedQuantity = orderedQuantity;
        this.requestMessage = requestMessage;
        this.deliveryLimitedAt = deliveryLimitedAt;
        status = OrderStatus.ACCEPTED;
    }

    public OrderForCreate cancel() {
        status = OrderStatus.CANCELED;
        return this;
    }

    public Long getOrderedBy() {
        return orderedBy;
    }

    public Long getOrderedCompany() {
        return orderedCompany;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public LocalDateTime getDeliveryLimitedAt() {
        return deliveryLimitedAt;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
