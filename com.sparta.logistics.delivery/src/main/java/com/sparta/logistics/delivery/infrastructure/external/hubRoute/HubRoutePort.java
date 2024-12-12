package com.sparta.logistics.delivery.infrastructure.external.hubRoute;

import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubRouteResponseDto;

public interface HubRoutePort {
    HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId);
}
