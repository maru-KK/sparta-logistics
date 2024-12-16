package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.output.CompanyDeliveryRoutePort;
import com.sparta.logistics.delivery.domain.vo.CompanyDeliveryRouteStatus;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.CompanyDeliveryRouteEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.CompanyDeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyDeliveryRouteAdapter implements CompanyDeliveryRoutePort {
    private final CompanyDeliveryRouteRepository companyDeliveryRouteRepository;

    @Override
    public void save(Long deliveryId, Long deliveryPersonId, CompanyResponse consumeCompany) {
        //todo 배송 예상 시간 계산 필요
        CompanyDeliveryRouteEntity entity = CompanyDeliveryRouteEntity.builder()
                .deliveryId(deliveryId)
                .originHubId(consumeCompany.hub().getHubId())
                .companyId(consumeCompany.companyId())
                .status(CompanyDeliveryRouteStatus.PENDING)
                .deliveryPersonId(deliveryPersonId)
                .build();

        companyDeliveryRouteRepository.save(entity);
    }
}
