package com.sparta.logistics.delivery.domain.vo;

public enum DeliveryLogStatus {

    PENDING_HUB("허브 대기중"),
    IN_HUB_DELIVERY("허브 이동중"),
    ARRIVED_HUB("목적지 허브 도착"),
    IN_DELIVERY("배송중");

    private String description;

    DeliveryLogStatus(String description) {
        this.description = description;
    }
}
