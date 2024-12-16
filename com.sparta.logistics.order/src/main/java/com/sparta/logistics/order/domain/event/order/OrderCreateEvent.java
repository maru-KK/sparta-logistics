package com.sparta.logistics.order.domain.event.order;

import com.sparta.logistics.order.domain.event.DomainEvent;

public class OrderCreateEvent implements DomainEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Long orderedBy;

    public OrderCreateEvent(Long orderId, Long productId, Integer quantity, Long orderedBy) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderedBy = orderedBy;
    }

    // for jackson serialize
    public OrderCreateEvent() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getOrderedBy() {
        return orderedBy;
    }
}
