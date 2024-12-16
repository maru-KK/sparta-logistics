package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.domain.DeliveryLog;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryLogEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryLog.DeliveryLogQueryDslRepository;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryLog.DeliveryLogRepository;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliveryLogSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryLogService {
    private final DeliveryLogRepository deliveryLogRepository;
    private final DeliveryLogQueryDslRepository deliveryLogQueryDslRepository;

    public DeliveryLog findOne(Long id) {
        DeliveryLogEntity entity = deliveryLogRepository.findById(id).orElseThrow(() -> new IllegalStateException("허브 배송 경로를 찾을 수 없습니다."));

        return entity.toDomain();
    }

    public Page<DeliveryLog> search(DeliveryLogSearchCondition searchCondition) {
        return deliveryLogQueryDslRepository.findAll(searchCondition).map(DeliveryLogEntity::toDomain);
    }
}
