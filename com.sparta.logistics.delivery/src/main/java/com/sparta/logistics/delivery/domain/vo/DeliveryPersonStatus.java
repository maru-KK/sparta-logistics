package com.sparta.logistics.delivery.domain.vo;

public enum DeliveryPersonStatus {
    DELIVERING("배송중"),
    OFF_DUTY("휴무"),
    AVAILABLE("배송 가능");


    private final String description;

    DeliveryPersonStatus(String description) {
        this.description = description;
    }
}
