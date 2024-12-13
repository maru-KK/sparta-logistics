package com.sparta.logistics.delivery.infrastructure.external.hubCompany;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface HubCompanyPort {
    HubResponseDto getHubById(Long hubId);

    CompanyResponse getCompanyById(@PathVariable Long companyId);

}
