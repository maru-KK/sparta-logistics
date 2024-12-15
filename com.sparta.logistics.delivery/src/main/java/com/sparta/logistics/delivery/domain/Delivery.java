package com.sparta.logistics.delivery.domain;

import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;

import java.time.LocalDateTime;

public record Delivery(
        Long deliveryId,
        DeliveryStatus status,
        String deliveryAddress,
        String recipientName,
        String recipientSnsId,
        Long orderId,
        Long originHubId,
        Long destinationHubId,
        LocalDateTime createdAt) {

    public static Delivery from(DeliveryEntity entity) {
        return new Delivery(
                entity.getDeliveryId(),
                entity.getStatus(),
                entity.getDeliveryAddress(),
                entity.getRecipientName(),
                entity.getRecipientSnsId(),
                entity.getOrderId(),
                entity.getOriginHubId(),
                entity.getDestinationHubId(),
                entity.getCreatedAt());
    }

    public void updateValidate() {
        if (deliveryAddress.trim().isEmpty()){
            throw new IllegalArgumentException("배송 주소는 빈 값이 될 수 없습니다.");
        }

        if (recipientName != null && (recipientName.trim().isEmpty())) {
            throw new IllegalArgumentException("수령인 이름은 빈 값이 될 수 없습니다.");
        }
        if (recipientSnsId != null && recipientSnsId.trim().isEmpty()) {
            throw new IllegalArgumentException("수령인 SNS ID는 빈 값이 될 수 없습니다.");
        }

        if (originHubId.equals(destinationHubId)) {
            throw new IllegalArgumentException("출발 허브와 도착 허브가 같을 수 없습니다.");
        }
    }
}
