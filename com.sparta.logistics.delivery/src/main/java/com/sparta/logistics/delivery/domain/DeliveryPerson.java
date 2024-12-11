package com.sparta.logistics.delivery.domain;

import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;

import java.time.LocalDateTime;

public record DeliveryPerson(
        Long deliveryPersonId,
        Long userId,
        Long hubId,
        String snsId,
        DeliveryPersonType type,
        DeliveryPersonStatus status,
        Integer sequence,
        LocalDateTime createdAt,
        String createdBy
) {

    public DeliveryPerson(Long userId, Long hubId, DeliveryPersonType type, DeliveryPersonStatus status, Integer sequence) {
        this(null, userId, hubId, null, type, status, sequence, null, null);
    }

    public static DeliveryPerson from(DeliveryPersonCreateRequestDto dto) {
        return new DeliveryPerson(dto.userId(), dto.hubId(), dto.type(), dto.status(), dto.sequence());
    }

    public static DeliveryPerson from(DeliveryPersonEntity entity) {
        return new DeliveryPerson(
                entity.getDeliveryPersonId(),
                entity.getDeliveryPersonId(),
                null,
                entity.getSnsId(),
                entity.getType(),
                entity.getStatus(),
                entity.getSequence(),
                entity.getCreatedAt(),
                entity.getCreatedBy());
    }

    public static DeliveryPerson from(DeliveryPersonEntity entity, Long hubId) {
        return new DeliveryPerson(
                entity.getDeliveryPersonId(),
                entity.getDeliveryPersonId(),
                hubId,
                entity.getSnsId(),
                entity.getType(),
                entity.getStatus(),
                entity.getSequence(),
                entity.getCreatedAt(),
                entity.getCreatedBy());
    }
}
