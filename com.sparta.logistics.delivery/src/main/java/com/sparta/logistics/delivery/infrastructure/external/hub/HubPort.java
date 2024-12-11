package com.sparta.logistics.delivery.infrastructure.external.hub;

import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubResponseDto;

public interface HubPort {
    HubResponseDto getHubById(Long hubId);
}
