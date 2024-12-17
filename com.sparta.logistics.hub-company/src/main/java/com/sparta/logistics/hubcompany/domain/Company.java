package com.sparta.logistics.hubcompany.domain;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Company {

    private final Long companyId;
    private final String name;
    private final String type;
    private final String address;
    private final HubEntity hub;
    private final Long userId;
}
