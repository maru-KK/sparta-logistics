package com.sparta.logistics.delivery.infrastructure.external.hub.dto;

public record CompanyResponse(
        Long hubId,
        Long userId,
        String address
) {
}
