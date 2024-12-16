package com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryLog;

import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryLogRepository extends JpaRepository<DeliveryLogEntity, Long> {
    DeliveryLogEntity findByDeliveryId(Long deliveryId);
}
