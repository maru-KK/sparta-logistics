package com.sparta.logistics.delivery.infrastructure.persistence.repository;

import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Long> {
    Page<DeliveryPersonEntity> findAllByIsDeletedFalse(Pageable pageable);

    @Query("SELECT d FROM p_delivery_person d " +
            "WHERE d.isDeleted = false " +
            "AND d.type = :type " +
            "AND d.status = :status " +
            "ORDER BY d.sequence ASC")
    List<DeliveryPersonEntity> findByHubDeliveryPerson(@Param("type") DeliveryPersonType type, @Param("status") DeliveryPersonStatus status);

    @Query("SELECT d " +
            "FROM p_delivery_person d " +
            "INNER JOIN p_hub_delivery_person h ON h.deliveryPersonId = d.deliveryPersonId " +
            "WHERE h.hubId = :hubId " +
            "AND d.isDeleted = false " +
            "AND d.type = :type " +
            "AND d.status = :status " +
            "ORDER BY d.sequence ASC")
    List<DeliveryPersonEntity> findByCompanyDeliveryPerson(@Param("type") DeliveryPersonType type, @Param("status") DeliveryPersonStatus status, @Param("hubId") Long hubId);

    @Query("SELECT MAX(d.sequence) " +
            "FROM p_delivery_person d " +
            "WHERE d.type = :type")
    Integer findMaxSequence(@Param("type") DeliveryPersonType type);
}
