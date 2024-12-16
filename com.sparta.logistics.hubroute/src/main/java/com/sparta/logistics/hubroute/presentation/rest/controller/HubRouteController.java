package com.sparta.logistics.hubroute.presentation.rest.controller;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.appliation.service.HubRouteService;
import com.sparta.logistics.hubroute.domain.HubRoute;
import com.sparta.logistics.hubroute.infrastructure.cache.adapter.HubRouteCacheAdapter;
import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hub-route")
public class HubRouteController {

    private final HubRouteService hubRouteService;
    private final HubRouteCacheAdapter hubRouteCacheAdapter;

    @PostMapping("/calculate-routes")
    public ResponseEntity<String> calculateAllRoutes() {
        hubRouteService.calculateAndSaveAllRoutes();
        return ResponseEntity.ok("모든 경로가 저장되었습니다.");
    }

    @GetMapping("/{hubRouteId}")
    public ResponseEntity<HubRouteResponseDto> getRouteById(@PathVariable("hubRouteId") Long hubRouteId) {
        Optional<HubRoute> cachedRoute = hubRouteCacheAdapter.findById(hubRouteId);
        if (cachedRoute.isPresent()) {
            return ResponseEntity.ok(cachedRoute.get().toDto());
        }
        HubRouteResponseDto hubRoute = hubRouteService.getRouteById(hubRouteId);
        hubRouteCacheAdapter.save(hubRoute.toDomain());
        return ResponseEntity.ok(hubRoute);
    }

    @GetMapping
    public ResponseEntity<List<HubRouteResponseDto>> getAllRoutes() {
        List<HubRouteResponseDto> routes = hubRouteService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping(params = {"origin", "destination"})
    public ResponseEntity<HubRouteResponseDto> getRouteByOriginAndDestination(
            @RequestParam("origin") Long originHubId,
            @RequestParam("destination") Long destinationHubId) {
        HubRouteResponseDto hubRoute = hubRouteService.getRouteByOriginAndDestination(originHubId, destinationHubId);
        return ResponseEntity.ok(hubRoute);
    }
}