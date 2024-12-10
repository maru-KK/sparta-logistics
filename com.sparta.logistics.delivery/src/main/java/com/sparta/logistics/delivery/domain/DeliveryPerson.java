package com.sparta.logistics.delivery.domain;

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
        Integer sequence,
        LocalDateTime createdAt,
        String createdBy
) {

    public DeliveryPerson(Long userId, Long hubId, DeliveryPersonType type, Integer sequence) {
        this(null, userId, hubId, null, type, sequence, null, null);
    }

    public static DeliveryPerson from(DeliveryPersonCreateRequestDto dto) {
        return new DeliveryPerson(dto.userId(), dto.hubId(), dto.type(), dto.sequence());
    }

    public static DeliveryPerson from(DeliveryPersonEntity entity) {
        return new DeliveryPerson(
                entity.getDeliveryPersonId(),
                entity.getUserId(),
                entity.getHubId(),
                entity.getSnsId(),
                entity.getType(),
                entity.getSequence(),
                entity.getCreatedAt(),
                entity.getCreatedBy());
    }
}
