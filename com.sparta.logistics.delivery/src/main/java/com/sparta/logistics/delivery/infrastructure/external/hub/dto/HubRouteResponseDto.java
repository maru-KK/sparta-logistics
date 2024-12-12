package com.sparta.logistics.delivery.infrastructure.external.hub.dto;

public record HubRouteResponseDto(
        Long hubRouteId,
        Long originHubId,
        Long destinationHubId,
        Integer actualDuration,
        Double actualDistance) {

}
