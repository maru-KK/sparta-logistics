package com.sparta.logistics.infra.persistence.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InfraRequestDto {
    private Long orderId;
    private String userName;
    private String productName;
    private int productQuantity;
    private String request;
    private String originHub;
    private String destinationHub;
    private String companyAddress;
    private String deliveryPersonName;
    private String snsAccount;
}