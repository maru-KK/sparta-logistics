package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "p_hub_delivery_person")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HubDeliveryPersonEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_delivery_person_id")
    private Long hubDeliveryPersonId;

    @Column(nullable = false)
    private Long deliveryPersonId;

    @Column(nullable = false)
    private Long hubId;

    public HubDeliveryPersonEntity(Long deliveryPersonId, Long hubId) {
        this.deliveryPersonId = deliveryPersonId;
        this.hubId = hubId;
    }
}
