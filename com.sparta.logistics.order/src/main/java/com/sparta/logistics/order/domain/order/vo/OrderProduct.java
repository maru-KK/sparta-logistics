package com.sparta.logistics.order.domain.order.vo;

import com.sparta.logistics.order.domain.order.Product;
import java.time.LocalDateTime;

public class OrderProduct {

    private Long id;

    private Long orderedBy;
    private Long consumeCompanyId;

    private Product product;
    private Integer orderedQuantity;

    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;
    private OrderStatus status;

    public OrderProduct(Long id, Long orderedBy, Long consumeCompanyId, Product product,
        Integer orderedQuantity, String requestMessage, LocalDateTime deliveryLimitedAt,
        OrderStatus status) {
        this.id = id;
        this.orderedBy = orderedBy;
        this.consumeCompanyId = consumeCompanyId;
        this.product = product;
        this.orderedQuantity = orderedQuantity;
        this.requestMessage = requestMessage;
        this.deliveryLimitedAt = deliveryLimitedAt;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderedBy() {
        return orderedBy;
    }

    public Long getConsumeCompanyId() {
        return consumeCompanyId;
    }

    public Product getProduct() {
        return product;
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

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }
}
