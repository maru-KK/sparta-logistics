package com.sparta.logistics.delivery.infrastructure.external.hub;

import com.sparta.logistics.delivery.infrastructure.external.hub.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface HubCompanyPort {
    HubResponseDto getHubById(Long hubId);

    CompanyResponse getCompanyById(@PathVariable Long companyId);

}
