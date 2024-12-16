package com.sparta.logistics.hubroute.domain;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HubRoute {
    private Long hubRouteId;
    private Long originHubId;
    private Long destinationHubId;
    private Integer actualDuration;
    private Double actualDistance;

    public HubRouteResponseDto toDto() {
        return new HubRouteResponseDto(
                this.hubRouteId,
                this.originHubId,
                this.destinationHubId,
                this.actualDuration,
                this.actualDistance
        );
    }

    public Long gethubRouteId(){return hubRouteId;}
    public Long getOriginHubId(){return originHubId;}
    public Long getDestinationHubId(){return destinationHubId;}
    public Double getActualDistance(){return actualDistance;}
    public Integer getActualDuration(){return actualDuration;}
}
