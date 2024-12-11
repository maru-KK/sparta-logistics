package com.sparta.logistics.hubroute.infrastructure.persistence.repository;

import com.sparta.logistics.hubroute.infrastructure.persistence.entity.HubRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HubRouteRepository extends JpaRepository<HubRouteEntity, Long> {
    Optional<HubRouteEntity> findByOriginHubIdAndDestinationHubId(Long originHubId, Long destinationHubId);
}
