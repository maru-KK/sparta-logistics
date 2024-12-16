package com.sparta.logistics.hubroute.appliation.dto;

import com.sparta.logistics.hubroute.domain.HubRoute;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

    public HubRoute toDomain(){
        return new HubRoute(this.hubRouteId, this.originHubId,
                this.destinationHubId, this.actualDuration, this.actualDistance);
    }
}
