package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubCompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto request, Long userId);
    CompanyEntity getCompanyById(Long companyId);
    HubResponseDto getHubByCompanyId(Long companyId);
    void updateCompany(Long companyId, CompanyRequestDto request, Long userId);
    List<HubCompanyResponseDto> getHubsAndCompaniesByUserId(Long userId);
}