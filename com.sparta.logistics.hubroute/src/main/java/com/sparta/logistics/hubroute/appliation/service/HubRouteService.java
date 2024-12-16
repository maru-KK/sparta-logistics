package com.sparta.logistics.hubroute.appliation.service;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;

import java.util.List;

public interface HubRouteService {
    void calculateAndSaveAllRoutes();
    void calculateAndSaveRoute(Long originHubId, Long destinationHubId);
    HubRouteResponseDto getRouteById(Long hubRouteId);
    List<HubRouteResponseDto> getAllRoutes();
    HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId);
}
