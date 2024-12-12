package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final HubService hubService;

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto request, Long userId) {

        HubEntity hub = hubService.getHubById(request.getHubId());

        CompanyEntity companyEntity = CompanyEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .address(request.getAddress())
                .hub(hub)
                .userId(request.getUserId())
                .userId(userId)
                .build();

        CompanyEntity savedCompany = companyRepository.save(companyEntity);

        return new CompanyResponseDto(savedCompany);
    }
}
