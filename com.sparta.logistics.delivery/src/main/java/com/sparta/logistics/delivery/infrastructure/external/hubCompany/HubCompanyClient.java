package com.sparta.logistics.delivery.infrastructure.external.hubCompany;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-company-service")
public interface HubCompanyClient {
    @GetMapping("/api/v1/hubs/{hubId}")
    ResponseEntity<HubResponseDto> getHubById(@PathVariable("hubId") Long hubId);

    @GetMapping("/api/v1/company/{companyId}")
    CompanyResponse getCompanyById(@PathVariable("companyId") Long companyId);
}
