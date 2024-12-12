package com.sparta.logistics.delivery.infrastructure.external.hubRoute;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubRouteAdapter implements HubRoutePort {
    private final HubRouteClient hubRouteClient;

    @Override
    public HubRouteResponseDto getRouteByOriginAndDestination(Long originHubId, Long destinationHubId) {
         return hubRouteClient.getRouteByOriginAndDestination(originHubId, destinationHubId).getBody();
    }
}