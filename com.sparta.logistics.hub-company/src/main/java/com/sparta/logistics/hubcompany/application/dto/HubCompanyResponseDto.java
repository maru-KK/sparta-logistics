package com.sparta.logistics.hubcompany.application.dto;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HubCompanyResponseDto {
    private final Long hubId;
    private final String hubName;
    private final String hubAddress;
    private final Long companyId;
    private final String companyName;
    private final String companyType;

    public HubCompanyResponseDto(HubEntity hub, CompanyEntity company) {
        this.hubId = hub.getHubId();
        this.hubName = hub.getName();
        this.hubAddress = hub.getAddress();
        this.companyId = company.getCompanyId();
        this.companyName = company.getName();
        this.companyType = company.getType();
    }
}