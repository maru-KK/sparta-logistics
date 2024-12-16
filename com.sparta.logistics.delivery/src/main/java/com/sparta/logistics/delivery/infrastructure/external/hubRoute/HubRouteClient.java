package com.sparta.logistics.delivery.infrastructure.external.hubRoute;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hub-route-service")
public interface HubRouteClient {
    @GetMapping(value = "/api/v1/hub-route", params = {"origin", "destination"})
    ResponseEntity<HubRouteResponseDto> getRouteByOriginAndDestination(
            @RequestParam("origin") Long originHubId,
            @RequestParam("destination") Long destinationHubId
    );
}
