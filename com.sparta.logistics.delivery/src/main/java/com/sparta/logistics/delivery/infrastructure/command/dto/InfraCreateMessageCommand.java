package com.sparta.logistics.delivery.infrastructure.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfraCreateMessageCommand {

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
