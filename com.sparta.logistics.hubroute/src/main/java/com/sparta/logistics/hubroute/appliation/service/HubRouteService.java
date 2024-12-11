package com.sparta.logistics.hubroute.appliation.service;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;

import java.util.List;

public interface HubRouteService {
    void calculateAndSaveAllRoutes();
    HubRouteEntity calculateAndSaveRoute(Long originHubId, Long destinationHubId);
    HubRouteResponseDto getRouteById(Long hubRouteId);
    List<HubRouteResponseDto> getAllRoutes();
    HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId);
}
