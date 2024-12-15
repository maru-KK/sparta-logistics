package com.sparta.logistics.delivery.application.dto;

import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;

public record DeliveryUpdateRequestDto(
        Long deliveryId,
        DeliveryStatus status,
        String deliveryAddress,
        String recipientName,
        String recipientSnsId,
        Long orderId,
        Long originHubId,
        Long destinationHubId

) {
    public Delivery toDomain() {
        return new Delivery(
                deliveryId,
                status,
                deliveryAddress,
                recipientName,
                recipientSnsId,
                orderId,
                originHubId,
                destinationHubId,
                null
        );
    }
}
