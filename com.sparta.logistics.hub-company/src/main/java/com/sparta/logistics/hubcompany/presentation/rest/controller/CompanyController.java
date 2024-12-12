package com.sparta.logistics.hubcompany.presentation.rest.controller;

import com.sparta.logistics.hubcompany.application.dto.CompanyRequestDto;
import com.sparta.logistics.hubcompany.application.dto.CompanyResponseDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.application.service.CompanyService;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody CompanyRequestDto request,
                                                @RequestHeader("X-User-Id") Long userId,
                                                @RequestHeader("X-Role") String role) {
        if (!"COMPANY".equals(role) && !"MASTER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("업체 생성 권한이 없습니다.");
        }

        CompanyResponseDto companyResponse = companyService.createCompany(request, userId);
        Long companyId = companyResponse.getCompanyId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Company created with ID: " + companyId);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyEntity> getCompany(@PathVariable Long companyId) {
        CompanyEntity company = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/{companyId}/hub")
    public ResponseEntity<HubResponseDto> getHubByCompanyId(@PathVariable Long companyId) {
        HubResponseDto hubResponse = companyService.getHubByCompanyId(companyId);
        return ResponseEntity.ok(hubResponse);
    }

}
