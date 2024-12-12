package com.sparta.logistics.product.infrastructure.client.dto;

import com.sparta.logistics.product.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailClientResponse {

    private Long id;
    private Long hubId;
    private String name;
    private String type;
    private String address;

    public Company toDomain() {
        return new Company(id, hubId, name, type);
    }
}
