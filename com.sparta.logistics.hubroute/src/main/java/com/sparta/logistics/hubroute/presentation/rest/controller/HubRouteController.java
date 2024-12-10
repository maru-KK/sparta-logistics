package com.sparta.logistics.hubroute.presentation.rest.controller;

import com.sparta.logistics.hubroute.appliation.dto.HubRouteResponseDto;
import com.sparta.logistics.hubroute.appliation.service.HubRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hub-route")
public class HubRouteController {

    private final HubRouteService hubRouteService;

    @GetMapping("/{hubRouteId}")
    public ResponseEntity<HubRouteResponseDto> getRouteById(@PathVariable("hubRouteId") Long hubRouteId) {
        HubRouteResponseDto hubRoute = hubRouteService.getRouteById(hubRouteId);
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