package com.sparta.logistics.delivery.infrastructure.persistence.repository;

import com.sparta.logistics.delivery.infrastructure.persistence.entity.HubDeliveryPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HubDeliveryPersonRepository extends JpaRepository<HubDeliveryPersonEntity, Long> {

    HubDeliveryPersonEntity findByDeliveryPersonId(Long deliveryPersonId);
}
