package com.sparta.logistics.delivery.domain.event;


import java.time.LocalDateTime;

public class OrderCreateEvent implements DomainEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Long orderedBy;

    private Long consumeCompanyId;
    private Long supplyCompanyId;
    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;

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

    public Long getConsumeCompanyId() {
        return consumeCompanyId;
    }

    public Long getSupplyCompanyId() {
        return supplyCompanyId;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public LocalDateTime getDeliveryLimitedAt() {
        return deliveryLimitedAt;
    }
}