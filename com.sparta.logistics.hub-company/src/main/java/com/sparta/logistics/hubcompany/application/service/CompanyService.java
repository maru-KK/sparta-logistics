package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubCompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto request, Actor actor);
    CompanyEntity getCompanyById(Long companyId);
    HubResponseDto getHubByCompanyId(Long companyId);
    void updateCompany(Long companyId, CompanyRequestDto request, Actor actor);
    List<HubCompanyResponseDto> getHubsAndCompaniesByUserId(Long userId);
}