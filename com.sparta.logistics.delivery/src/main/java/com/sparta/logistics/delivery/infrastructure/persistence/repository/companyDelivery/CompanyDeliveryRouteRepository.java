package com.sparta.logistics.delivery.infrastructure.persistence.repository.companyDelivery;

import com.sparta.logistics.delivery.infrastructure.persistence.entity.CompanyDeliveryRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDeliveryRouteRepository extends JpaRepository<CompanyDeliveryRouteEntity, Long> {
    CompanyDeliveryRouteEntity findByDeliveryId(Long deliveryId);
}
