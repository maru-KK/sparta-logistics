package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "p_delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class DeliveryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_sns_id")
    private String recipientSnsId;

    @Column(name = "order_id", unique = true)
    private Long orderId;

    @Column(name = "origin_hub_id")
    private Long originHubId;

    @Column(name = "destination_hub_id")
    private Long destinationHubId;

    public static DeliveryEntity toEntity(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany) {
        return DeliveryEntity.builder()
                .status(DeliveryStatus.PENDING_HUB)
                .deliveryAddress(supplyCompany.address())
                .recipientName(userInfo.username())
                .recipientSnsId(userInfo.snsAccount())
                .orderId(requestDto.orderId())
                .originHubId(supplyCompany.hub().getHubId())
                .destinationHubId(consumeCompany.hub().getHubId())
                .build();
    }

    public void update(Delivery updateRequest, Long userId) {
        this.status = updateRequest.status();
        this.deliveryAddress = updateRequest.deliveryAddress();
        this.recipientName = updateRequest.recipientName();
        this.recipientSnsId = updateRequest.recipientSnsId();
        this.originHubId = updateRequest.originHubId();
        this.destinationHubId = updateRequest.destinationHubId();
        setUpdatedBy(userId);
    }

    public void updateStatus(String status, Long userId) {
        this.status = DeliveryStatus.valueOf(status);
        setUpdatedBy(userId);
    }
}
