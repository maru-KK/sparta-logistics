package com.sparta.logistics.delivery.presentation.dto;

import com.sparta.logistics.delivery.domain.DeliveryLog;
import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;

import java.time.LocalDateTime;

public record DeliveryLogResponse(
        Long deliveryLogId,
        Long deliveryId,
        Long originHubId,
        Long destinationHubId,
        Long deliveryPersonId,
        DeliveryLogStatus status,
        Integer estimatedDuration,
        Integer actualDuration,
        double actualDistance,
        LocalDateTime createdAt
) {

    public static DeliveryLogResponse from(DeliveryLog deliveryLog) {
        return new DeliveryLogResponse(
                deliveryLog.deliveryLogId(),
                deliveryLog.deliveryId(),
                deliveryLog.originHubId(),
                deliveryLog.destinationHubId(),
                deliveryLog.deliveryPersonId(),
                deliveryLog.status(),
                deliveryLog.estimatedDuration(),
                deliveryLog.actualDuration(),
                deliveryLog.actualDistance(),
                deliveryLog.createdAt()
        );
    }
}