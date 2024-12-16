package com.sparta.logistics.delivery.presentation.dto;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;

public record DeliveryPersonUpdateRequestDto(
        Long deliveryPersonId,
        Long hubId,
        String snsId,
        DeliveryPersonType type,
        DeliveryPersonStatus status
) {
    public DeliveryPerson from() {
        return new DeliveryPerson(deliveryPersonId, null, hubId, snsId, type, status, null, null, null);
    }
}
