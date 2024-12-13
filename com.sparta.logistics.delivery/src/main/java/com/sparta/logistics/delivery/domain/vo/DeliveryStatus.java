package com.sparta.logistics.delivery.domain.vo;

public enum DeliveryStatus {
    PENDING_HUB("허브 대기중"),
    IN_HUB_DELIVERY("허브 이동중"),
    ARRIVED_HUB("목적지 허브 도착"),
    IN_DELIVERY("배송중"),
    IN_COMPANY_DELIVERY("업체 이동중");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
