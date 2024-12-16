package com.sparta.logistics.delivery.domain.event;

import java.time.LocalDateTime;

public class DeliveryCreateEvent implements DomainEvent {

    private Long deliveryId;
    private String status;
    private String deliveryAddress;
    private String recipientName;
    private String recipientSnsId;
    private Long orderId;
    private Long originHubId;
    private Long destinationHubId;
    private LocalDateTime createdAt;

    public DeliveryCreateEvent(Long deliveryId, String status, String deliveryAddress,
        String recipientName, String recipientSnsId, Long orderId, Long originHubId,
        Long destinationHubId, LocalDateTime createdAt) {
        this.deliveryId = deliveryId;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.recipientName = recipientName;
        this.recipientSnsId = recipientSnsId;
        this.orderId = orderId;
        this.originHubId = originHubId;
        this.destinationHubId = destinationHubId;
        this.createdAt = createdAt;
    }

    // for jackson serialize
    public DeliveryCreateEvent() {

    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientSnsId() {
        return recipientSnsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getOriginHubId() {
        return originHubId;
    }

    public Long getDestinationHubId() {
        return destinationHubId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
