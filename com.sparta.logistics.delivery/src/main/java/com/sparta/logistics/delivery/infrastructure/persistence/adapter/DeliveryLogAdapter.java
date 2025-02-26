package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.dto.DeliveryStatusUpdateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryLogPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryLogEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryLog.DeliveryLogRepository;
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

    @Override
    public void update(DeliveryStatusUpdateRequestDto.DeliveryLogStatusDto request, Long userId) {
        DeliveryLogEntity deliveryLogEntity = deliveryLogRepository.findByDeliveryId(request.deliveryId());
        if (!deliveryLogEntity.getDeliveryPersonId().equals(userId)) {
            throw new IllegalArgumentException("해당 허브 배송 담당자가 아닙니다.");
        }

        deliveryLogEntity.setStatus(request.status());
    }
}
