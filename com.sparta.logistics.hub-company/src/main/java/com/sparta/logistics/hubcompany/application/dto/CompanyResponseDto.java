package com.sparta.logistics.hubcompany.application.dto;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import lombok.Getter;

@Getter
public class CompanyResponseDto {
    private final Long companyId;
    private final String name;
    private final String type;
    private final String address;
    private final Long hubId;
    private final Long userId;

    public CompanyResponseDto(CompanyEntity companyEntity) {
        this.companyId = companyEntity.getCompanyId();
        this.name = companyEntity.getName();
        this.type = companyEntity.getType();
        this.address = companyEntity.getAddress();
        this.hubId = companyEntity.getHub().getHubId();
        this.userId = companyEntity.getUserId();
    }
}
