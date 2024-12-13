package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;

public interface DeliveryPort {
    Delivery save(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany);


}
