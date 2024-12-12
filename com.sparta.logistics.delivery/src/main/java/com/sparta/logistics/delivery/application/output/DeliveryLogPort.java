package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;

public interface DeliveryLogPort {
    void save(Delivery delivery, HubRouteResponseDto hubRouteInfo, DeliveryPerson nextHubDeliveryPerson);
}
