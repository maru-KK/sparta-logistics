package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "p_delivery_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class DeliveryLogEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_log_id")
    private Long deliveryLogId;

    @Column(name = "delivery_id", nullable = false)
    private Long deliveryId;

    @Column(name = "origin_hub_id", nullable = false)
    private Long originHubId;

    @Column(name = "destination_hub_id", nullable = false)
    private Long destinationHubId;

    @Column(name = "delivery_person_id", nullable = false)
    private Long deliveryPersonId;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryLogStatus status;

    @Column(name = "estimated_duration", nullable = false)
    private Integer estimatedDuration;

    @Column(name = "actual_duration", nullable = false)
    private Integer actualDuration;

    @Column(name = "actual_distance", nullable = false)
    private double actualDistance;

    public static DeliveryLogEntity of(Delivery delivery, HubRouteResponseDto hubRouteInfo, DeliveryPerson hubDeliveryPerson) {
        return DeliveryLogEntity.builder()
                .deliveryId(delivery.deliveryId())
                .originHubId(hubRouteInfo.originHubId())
                .destinationHubId(hubRouteInfo.destinationHubId())
                .deliveryPersonId(hubDeliveryPerson.deliveryPersonId())
                .status(DeliveryLogStatus.PENDING_HUB)
                .estimatedDuration(60)
                .actualDuration(hubRouteInfo.actualDuration())
                .actualDistance(hubRouteInfo.actualDistance())
                .build();
    }

    public void setStatus(DeliveryLogStatus status) {
        this.status = status;
    }
}
