package com.sparta.logistics.delivery.infrastructure.external.hub;

import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubAdapter implements HubPort{
    private final HubClient hubClient;

    @Override
    public HubResponseDto getHubById(Long hubId) {

        return hubClient.getHubById(hubId).getBody();
    }
}
