package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubCompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.CompanyRepository;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubRepository;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final HubService hubService;
    private final HubRepository hubRepository;

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

    @Override
    public CompanyEntity getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("업체를 찾을 수 없습니다: " + companyId));
    }

    @Override
    public HubResponseDto getHubByCompanyId(Long companyId){
        CompanyEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("업체를 찾을 수 없습니다: " + companyId));

        HubEntity hub = company.getHub();

        return new HubResponseDto(hub);
    }

    @Override
    public void updateCompany(Long companyId, CompanyRequestDto request, Long userId) {
        CompanyEntity existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("업체를 찾을 수 없습니다: " + companyId));

        HubEntity hub = null;
        if (request.getHubId() != null) {
            hub = hubRepository.findById(request.getHubId())
                    .orElseThrow(() -> new ResourceNotFoundException("허브를 찾을 수 없습니다: " + request.getHubId()));
        }

        CompanyEntity updatedCompany = CompanyEntity.builder()
                .companyId(existingCompany.getCompanyId())
                .name(request.getName() != null ? request.getName() : existingCompany.getName())
                .type(request.getType() != null ? request.getType() : existingCompany.getType())
                .address(request.getAddress() != null ? request.getAddress() : existingCompany.getAddress())
                .hub(hub != null ? hub : existingCompany.getHub())
                .userId(existingCompany.getUserId())
                .build();

        companyRepository.save(updatedCompany);
    }

    @Override
    public List<HubCompanyResponseDto> getHubsAndCompaniesByUserId(Long userId) {
        List<CompanyEntity> companies = companyRepository.findByUserId(userId);

        return companies.stream()
                .map(company -> {
                    HubEntity hub = hubRepository.findById(company.getHub().getHubId())
                            .orElseThrow(() -> new ResourceNotFoundException("허브를 찾을 수 없습니다: " + company.getHub().getHubId()));
                    return new HubCompanyResponseDto(hub, company);
                })
                .collect(Collectors.toList());
    }

}
