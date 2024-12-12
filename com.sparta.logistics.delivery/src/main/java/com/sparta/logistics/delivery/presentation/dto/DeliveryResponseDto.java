package com.sparta.logistics.delivery.presentation.dto;

import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;

import java.time.LocalDateTime;

public record DeliveryResponseDto(
        Long deliveryId,
        DeliveryStatus status,
        String deliveryAddress,
        String recipientName,
        String recipientSnsId,
        Long orderId,
        Long originHubId,
        Long destinationHubId,
        LocalDateTime createdAt) {

    public static DeliveryResponseDto from(Delivery delivery) {
        return new DeliveryResponseDto(
                delivery.deliveryId(),
                delivery.status(),
                delivery.deliveryAddress(),
                delivery.recipientName(),
                delivery.recipientSnsId(),
                delivery.orderId(),
                delivery.originHubId(),
                delivery.destinationHubId(),
                delivery.createdAt());
    }
}
