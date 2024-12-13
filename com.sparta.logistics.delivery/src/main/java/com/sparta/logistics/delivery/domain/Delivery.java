package com.sparta.logistics.delivery.domain;

import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;

import java.time.LocalDateTime;

public record Delivery(
        Long deliveryId,
        DeliveryStatus status,
        String deliveryAddress,
        String recipientName,
        String recipientSnsId,
        Long orderId,
        Long originHubId,
        Long destinationHubId,
        LocalDateTime createdAt) {

    public static Delivery from(DeliveryEntity entity) {
        return new Delivery(
                entity.getDeliveryId(),
                entity.getStatus(),
                entity.getDeliveryAddress(),
                entity.getRecipientName(),
                entity.getRecipientSnsId(),
                entity.getOrderId(),
                entity.getOriginHubId(),
                entity.getDestinationHubId(),
                entity.getCreatedAt());
    }
}
