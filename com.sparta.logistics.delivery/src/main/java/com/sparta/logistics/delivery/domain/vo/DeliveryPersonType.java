package com.sparta.logistics.delivery.domain.vo;

public enum DeliveryPersonType {
    HUB_DELIVERY("허브 배송 담당자"),
    COMPANY_DELIVERY("업체 배송 담당자");

    private final String description;

    DeliveryPersonType(String description) {
        this.description = description;
    }
}
