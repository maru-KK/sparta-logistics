package com.sparta.logistics.delivery.application.dto;

import com.sparta.logistics.delivery.domain.vo.CompanyDeliveryRouteStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;

public record DeliveryStatusUpdateRequestDto() {

    public record DeliveryLogStatusDto(
            Long deliveryId,
            DeliveryLogStatus status
    ) {
    }

    public record CompanyDeliveryLogStatusDto(
            Long deliveryId,
            CompanyDeliveryRouteStatus status) {
    }
}
