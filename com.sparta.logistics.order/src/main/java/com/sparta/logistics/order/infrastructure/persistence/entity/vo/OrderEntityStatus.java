package com.sparta.logistics.order.infrastructure.persistence.entity.vo;

import com.sparta.logistics.order.domain.order.vo.OrderStatus;

public enum OrderEntityStatus {

    ACCEPTED, IN_TRANSIT, IN_DELIVER, CANCELED;

    public OrderStatus toDomain() {
        return OrderStatus.valueOf(this.name());
    }

    public static OrderEntityStatus of(String status) {
        for (OrderEntityStatus orderStatus : OrderEntityStatus.values()) {
            if (orderStatus.name().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        return null;
    }
}
