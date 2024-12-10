package com.sparta.logistics.hubcompany.application.dto;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class HubResponseDto {

    private final Long hubId;
    private final String name;
    private final String address;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final Long userId;

    public HubResponseDto(HubEntity hubEntity) {
        this.hubId = hubEntity.getHubId();
        this.name = hubEntity.getName();
        this.address = hubEntity.getAddress();
        this.latitude = hubEntity.getLatitude();
        this.longitude = hubEntity.getLongitude();
        this.userId = hubEntity.getUserId();
    }
}

