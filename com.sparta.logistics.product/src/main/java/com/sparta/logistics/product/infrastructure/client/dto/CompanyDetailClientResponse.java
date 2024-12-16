package com.sparta.logistics.product.infrastructure.client.dto;

import com.sparta.logistics.product.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailClientResponse {

    private Long hubId;
    private String hubName;
    private String hubAddress;
    private Long companyId;
    private String companyName;
    private String companyType;

    public Company toDomain() {
        return new Company(companyId, hubId, companyName, companyType);
    }
}
