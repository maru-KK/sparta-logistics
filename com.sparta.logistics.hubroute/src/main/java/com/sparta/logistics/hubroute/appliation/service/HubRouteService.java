package com.sparta.logistics.hubroute.appliation.service;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;

import java.util.List;

public interface HubRouteService {
    HubRouteResponseDto getRouteById(Long hubRouteId);
    List<HubRouteResponseDto> getAllRoutes();
    HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId);
}
