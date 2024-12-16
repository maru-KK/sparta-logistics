package com.sparta.logistics.delivery.domain;

import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;

import java.time.LocalDateTime;

public record DeliveryLog(
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


}
