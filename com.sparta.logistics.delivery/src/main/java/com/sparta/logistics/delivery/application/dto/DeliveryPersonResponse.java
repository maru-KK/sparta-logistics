package com.sparta.logistics.delivery.application.dto;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryPersonResponse {

    private Long id;
    private Long userId;
    private Long hubId;
    private String snsId;
    private DeliveryPersonType type;
    private DeliveryPersonStatus status;
    private Integer sequence;
    private LocalDateTime createdAt;
    private String createdBy;

    public DeliveryPersonResponse(Long id, Long userId, Long hubId, String snsId, DeliveryPersonType type, DeliveryPersonStatus status, Integer sequence, LocalDateTime createdAt, String createdBy) {
        this.id = id;
        this.userId = userId;
        this.hubId = hubId;
        this.snsId = snsId;
        this.type = type;
        this.status = status;
        this.sequence = sequence;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
    }

    public static DeliveryPersonResponse from(DeliveryPerson domain) {
        return new DeliveryPersonResponse(
                domain.deliveryPersonId(),
                domain.userId(),
                domain.hubId(),
                domain.snsId(),
                domain.type(),
                domain.status(),
                domain.sequence(),
                domain.createdAt(),
                domain.createdBy());
    }
}
