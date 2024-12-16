package com.sparta.logistics.delivery.domain.vo;

import lombok.Getter;

@Getter
public enum CompanyDeliveryRouteStatus {
    PENDING("대기중"),
    IN_DELIVERY("업체 이동중"),
    COMPLETED("배송 완료");

    private final String description;

    CompanyDeliveryRouteStatus(String description) {
        this.description = description;
    }
}
