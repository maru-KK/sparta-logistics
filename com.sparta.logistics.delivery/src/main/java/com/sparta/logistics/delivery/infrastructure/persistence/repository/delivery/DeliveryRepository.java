package com.sparta.logistics.delivery.infrastructure.persistence.repository.delivery;

import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    boolean existsByOrderId(Long orderId);
}
