package com.sparta.logistics.hubroute.infrastructure.persistence.repository;

import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HubRouteRepository extends JpaRepository<HubRouteEntity, Long> {
    Optional<HubRouteEntity> findByOriginHubIdAndDestinationHubId(Long originHubId, Long destinationHubId);
}
