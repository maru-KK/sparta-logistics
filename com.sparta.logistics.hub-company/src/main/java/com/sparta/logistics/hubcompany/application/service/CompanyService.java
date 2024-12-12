package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto request, Long userId);
    CompanyEntity getCompanyById(Long companyId);
    HubResponseDto getHubByCompanyId(Long companyId);
}