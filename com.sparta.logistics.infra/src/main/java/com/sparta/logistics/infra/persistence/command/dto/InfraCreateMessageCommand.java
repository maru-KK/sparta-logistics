package com.sparta.logistics.infra.persistence.command.dto;

import com.sparta.logistics.infra.domain.ai.Ai;
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

    public Ai toDomain() {
        return new Ai(
                orderId, userName, productName, productQuantity, request, originHub,
                destinationHub, companyAddress, deliveryPersonName, snsAccount
        );
    }
}
