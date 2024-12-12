package com.sparta.logistics.delivery.infrastructure.external.hubCompany;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubCompanyCompanyAdapter implements HubCompanyPort {
    private final HubCompanyClient hubCompanyClient;

    @Override
    public HubResponseDto getHubById(Long hubId) {

        return hubCompanyClient.getHubById(hubId).getBody();
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return hubCompanyClient.getCompanyById(companyId);
    }
}
