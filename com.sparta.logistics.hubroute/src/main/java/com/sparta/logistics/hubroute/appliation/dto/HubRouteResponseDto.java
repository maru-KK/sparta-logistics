package com.sparta.logistics.hubroute.appliation.dto;

import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import lombok.Getter;

@Getter
public class HubRouteResponseDto {
    private final Long hubRouteId;
    private final Long originHubId;
    private final Long destinationHubId;
    private final Integer actualDuration;
    private final Double actualDistance;

    public HubRouteResponseDto(HubRouteEntity hubRouteEntity) {
        this.hubRouteId = hubRouteEntity.getHubRouteId();
        this.originHubId = hubRouteEntity.getOriginHubId();
        this.destinationHubId = hubRouteEntity.getDestinationHubId();
        this.actualDuration = hubRouteEntity.getActualDuration();
        this.actualDistance = hubRouteEntity.getActualDistance();
    }
}
