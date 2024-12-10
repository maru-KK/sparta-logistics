package com.sparta.logistics.delivery.infrastructure.external.hub.dto;

import java.math.BigDecimal;

public record HubResponseDto(
        Long hubId,
        String name,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        Long userId) {
}
