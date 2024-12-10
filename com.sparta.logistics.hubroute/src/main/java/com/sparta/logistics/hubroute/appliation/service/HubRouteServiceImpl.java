package com.sparta.logistics.hubroute.appliation.service;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.infrastructure.client.HubCompanyServiceFeignClient;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import com.sparta.logistics.hubroute.infrastructure.persistence.repository.HubRouteRepository;
import com.sparta.logistics.hubroute.presentation.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HubRouteServiceImpl implements HubRouteService {

    private final HubRouteRepository hubRouteRepository;
    private final HubCompanyServiceFeignClient hubCompanyServiceFeignClient;

    @Override
    public HubRouteResponseDto getRouteById(Long hubRouteId) {
        HubRouteEntity hubRoute = hubRouteRepository.findById(hubRouteId)
                .orElseThrow(() -> new ResourceNotFoundException("경로를 찾을 수 없습니다: " + hubRouteId));
        return new HubRouteResponseDto(hubRoute);
    }

    @Override
    public List<HubRouteResponseDto> getAllRoutes() {
        List<HubRouteEntity> routes = hubRouteRepository.findAll();
        return routes.stream()
                .map(HubRouteResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId) {
        HubRouteEntity hubRoute = hubRouteRepository.findByOriginHubIdAndDestinationHubId(originHubId, destinationHubId)
                .orElseThrow(() -> new ResourceNotFoundException("경로를 찾을 수 없습니다: 출발 허브("
                        + originHubId + "), 도착 허브(" + destinationHubId + ")"));
        return new HubRouteResponseDto(hubRoute);
    }
}