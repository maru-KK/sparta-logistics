package com.sparta.logistics.delivery.infrastructure.persistence.repository;

import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByIsDeletedFalse(Pageable pageable);
}
