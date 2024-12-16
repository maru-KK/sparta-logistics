package com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto;

import lombok.Getter;

import java.math.BigDecimal;

public record CompanyResponse(
         Long companyId,
         String name,
         String type,
         String address,
         HubEntity hub,
         Long userId
) {
    @Getter
    public static class HubEntity{
        private Long hubId;
        private String name;
        private String address;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private Long userId;
    }
}
