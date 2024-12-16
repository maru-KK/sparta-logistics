package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;

public interface CompanyDeliveryRoutePort {
    void save(Long deliveryId, Long deliveryPersonId, CompanyResponse consumeCompany);
}
