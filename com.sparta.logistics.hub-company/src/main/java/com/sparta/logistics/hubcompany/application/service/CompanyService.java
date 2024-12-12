package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto request, Long userId);
}