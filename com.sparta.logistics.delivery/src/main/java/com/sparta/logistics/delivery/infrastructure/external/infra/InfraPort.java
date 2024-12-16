package com.sparta.logistics.delivery.infrastructure.external.infra;

import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.product.ProductDetailResponse;

public interface InfraPort {
    void send(Long orderId, UserDetailResponse userInfo, ProductDetailResponse productDetailResponse, Integer quantity, String request, CompanyResponse supplyCompany, CompanyResponse consumeCompany, UserDetailResponse nextCompanyDeliveryPerson);
}
