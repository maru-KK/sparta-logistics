package com.sparta.logistics.delivery.infrastructure.external.infra;

public record InfraRequestDto(
        Long orderId,
        String userName,
        String productName,
        int productQuantity,
        String request,
        String originHub,
        String destinationHub,
        String companyAddress,
        String deliveryPersonName,
        String snsAccount
) {
}
