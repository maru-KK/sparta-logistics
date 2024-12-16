package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import com.sparta.logistics.delivery.domain.vo.CompanyDeliveryRouteStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "p_company_delivery_route")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
public class CompanyDeliveryRouteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "origin_hub_id")
    private Long originHubId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "expected_distance")
    private Double expectedDistance;

    @Column(name = "expected_duration")
    private Integer expectedDuration;

    @Column(name = "actual_distance")
    private Double actualDistance;

    @Column(name = "actual_duration")
    private Integer actualDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyDeliveryRouteStatus status;

    @Column(name = "delivery_person_id")
    private Long deliveryPersonId;

    @Column(name = "sequence")
    private Integer sequence;

    public void setStatus(CompanyDeliveryRouteStatus status) {
        this.status = status;
    }
}