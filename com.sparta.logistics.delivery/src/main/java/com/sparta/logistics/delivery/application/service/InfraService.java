package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.output.InfraPort;
import com.sparta.logistics.delivery.infrastructure.command.dto.InfraCreateMessageCommand;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.product.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InfraService {

    private final InfraPort infraPort;

    public void sendMessage(Long orderId, UserDetailResponse userInfo, ProductDetailResponse productDetailResponse,
                            Integer quantity, String request, CompanyResponse supplyCompany, CompanyResponse consumeCompany,
                            UserDetailResponse nextCompanyDeliveryPerson
    ) {
        InfraCreateMessageCommand command = new InfraCreateMessageCommand(
                orderId,
                userInfo.username(),
                productDetailResponse.getName(),
                quantity,
                request,
                supplyCompany.hub().getAddress(),
                consumeCompany.hub().getAddress(),
                consumeCompany.address(),
                nextCompanyDeliveryPerson.username(),
                userInfo.snsAccount()
        );
        infraPort.sendMessage(command);
    }
}
