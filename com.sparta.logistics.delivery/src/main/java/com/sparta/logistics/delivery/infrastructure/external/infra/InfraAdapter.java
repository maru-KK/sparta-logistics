package com.sparta.logistics.delivery.infrastructure.external.infra;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.product.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfraAdapter implements InfraPort {
    private final InfraFeignClient infraFeignClient;

    @Override
    public void send(Long orderId, UserDetailResponse userInfo, ProductDetailResponse productDetailResponse, Integer quantity, String request, CompanyResponse supplyCompany, CompanyResponse consumeCompany, UserDetailResponse nextCompanyDeliveryPerson) {
        InfraRequestDto infraRequestDto = new InfraRequestDto(
                orderId,
                userInfo.username(),
                productDetailResponse.getName(),
                quantity,
                request,
                supplyCompany.hub().getAddress(),
                consumeCompany.hub().getAddress(),
                consumeCompany.address(),
                nextCompanyDeliveryPerson.username()
        );

        infraFeignClient.send(infraRequestDto);
    }
}
