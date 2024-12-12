package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.output.DeliveryLogPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubRouteResponseDto;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryLogEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.DeliveryLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryLogAdapter implements DeliveryLogPort {
    private final DeliveryLogRepository deliveryLogRepository;

    @Override
    public void save(Delivery delivery, HubRouteResponseDto hubRouteInfo, DeliveryPerson hubDeliveryPerson) {
        DeliveryLogEntity deliveryLogEntity = DeliveryLogEntity.of(delivery, hubRouteInfo, hubDeliveryPerson);

        deliveryLogRepository.save(deliveryLogEntity);
    }
}
