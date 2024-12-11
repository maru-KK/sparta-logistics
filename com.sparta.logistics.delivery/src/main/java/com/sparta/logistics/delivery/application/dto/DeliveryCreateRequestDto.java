package com.sparta.logistics.delivery.application.dto;

import java.time.LocalDateTime;

public record DeliveryCreateRequestDto(
        Long orderId,
        Long consumeCompanyId,
        Long supplyCompanyId,
        String request,
        LocalDateTime deliveryLimitedAt

) {
}
