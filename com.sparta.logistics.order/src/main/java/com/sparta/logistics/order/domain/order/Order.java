package com.sparta.logistics.order.domain.order;

import com.sparta.logistics.order.domain.event.OrderCreateEvent;
import com.sparta.logistics.order.domain.order.vo.OrderStatus;
import java.time.LocalDateTime;

public class Order {

    private Long id;

    private Long orderedBy;
    private Long consumeCompanyId;
    private Long supplyCompanyId;

    private Long productId;
    private Integer quantity;

    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;
    private OrderStatus status;

    public Order(
        Long id, Long orderedBy, Long consumeCompanyId, Long supplyCompanyId,
        Long productId, Integer quantity, String requestMessage,
        LocalDateTime deliveryLimitedAt, OrderStatus status
    ) {
        this.id = id;
        this.orderedBy = orderedBy;
        this.consumeCompanyId = consumeCompanyId;
        this.supplyCompanyId = supplyCompanyId;
        this.productId = productId;
        this.quantity = quantity;
        this.requestMessage = requestMessage;
        this.deliveryLimitedAt = deliveryLimitedAt;
        this.status = status;
    }

    public OrderCreateEvent createEvent() {
        return new OrderCreateEvent(id, productId, quantity, orderedBy);
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

    public Long getSupplyCompanyId() {
        return supplyCompanyId;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
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
