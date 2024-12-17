package com.sparta.logistics.delivery.application.dto;

import com.sparta.logistics.delivery.domain.event.OrderCreateEvent;

import java.time.LocalDateTime;

public record DeliveryCreateRequestDto(
        Long orderId,
        Long consumeCompanyId,
        Long supplyCompanyId,
        String request,
        Long productId,
        Integer quantity,
        LocalDateTime deliveryLimitedAt,
         Long orderedBy


) {
    public static DeliveryCreateRequestDto from(OrderCreateEvent orderCreateEvent) {
        return new DeliveryCreateRequestDto(
                orderCreateEvent.getOrderId(),
                orderCreateEvent.getConsumeCompanyId(),
                orderCreateEvent.getSupplyCompanyId(),
                orderCreateEvent.getRequestMessage(),
                orderCreateEvent.getProductId(),
                orderCreateEvent.getQuantity(),
                orderCreateEvent.getDeliveryLimitedAt(),
                orderCreateEvent.getOrderedBy()
        );
    }
}
