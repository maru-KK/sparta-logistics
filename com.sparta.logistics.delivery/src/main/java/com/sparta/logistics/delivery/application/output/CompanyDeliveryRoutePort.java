package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.application.dto.DeliveryStatusUpdateRequestDto;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;

public interface CompanyDeliveryRoutePort {
    void save(Long deliveryId, Long deliveryPersonId, CompanyResponse consumeCompany);

    void update(DeliveryStatusUpdateRequestDto.CompanyDeliveryLogStatusDto requestDto, Long userId);
}
