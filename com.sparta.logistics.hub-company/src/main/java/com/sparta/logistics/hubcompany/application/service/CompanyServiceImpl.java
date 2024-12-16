package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubCompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.auth.AuthPort;
import com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.CompanyRepository;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubRepository;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.InvalidAccessResourceException;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Role;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.AccessDeniedException;
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
    private final AuthPort authPort;

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto request, Actor actor){
        HubEntity hub = hubService.getHubById(request.getHubId());
        UserDetailResponse user = authPort.findUser(request.getUserId());

        validateCreatePermission(request.getHubId(), actor);

        CompanyEntity companyEntity = CompanyEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .address(request.getAddress())
                .hub(hub)
                .userId(request.getUserId())
                .build();

        CompanyEntity savedCompany = companyRepository.save(companyEntity);

        return new CompanyResponseDto(savedCompany);
    }

    private void validateCreatePermission(Long hubId, Actor actor) {
        if (actor.role() == Role.MASTER) {
            return;
        }

        if (actor.role() == Role.HUB) {
            if (!hubId.equals(actor.userId())) {
                throw new InvalidAccessResourceException("해당 허브에 대한 권한이 없습니다.");
            }
        }

        if (actor.role() == Role.COMPANY || actor.role() == Role.DELIVERY) {
            throw new InvalidAccessResourceException("업체 생성 권한이 없습니다.");
        }
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
    public void updateCompany(Long companyId, CompanyRequestDto request, Actor actor) {
        CompanyEntity existingCompany = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("업체를 찾을 수 없습니다: " + companyId));

        validateUpdatePermission(existingCompany, request.getHubId(), actor);

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

    private void validateUpdatePermission(CompanyEntity company, Long requestedHubId, Actor actor) {
        if (actor.role() == Role.MASTER) {
            return;
        }

        if (actor.role() == Role.HUB) {
            Long hubIdToCheck = requestedHubId != null ? requestedHubId : company.getHub().getHubId();

            HubEntity hub = hubRepository.findById(hubIdToCheck)
                    .orElseThrow(() -> new ResourceNotFoundException("허브를 찾을 수 없습니다: " + hubIdToCheck));

            if (!hub.getUserId().equals(actor.userId())) {
                throw new AccessDeniedException("권한이 없습니다. 요청한 허브에 대한 접근 권한이 필요합니다.");
            }

        } else if (actor.role() == Role.COMPANY) {
            if (!company.getUserId().equals(actor.userId())) {
                throw new AccessDeniedException("권한이 없습니다. 본인 소유의 업체만 수정할 수 있습니다.");
            }
        } else {
            throw new AccessDeniedException("권한이 없습니다. 업체 수정은 MASTER, 담당 HUB, 본인 COMPANY만 가능합니다.");
        }
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
