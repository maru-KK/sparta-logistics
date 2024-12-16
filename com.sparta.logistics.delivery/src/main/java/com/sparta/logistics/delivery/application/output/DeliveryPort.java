package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.dto.DeliveryStatusUpdateRequestDto;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliverySearchCondition;
import org.springframework.data.domain.Page;

public interface DeliveryPort {
    Delivery save(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany);


    Delivery findOne(Long deliveryId);

    Page<Delivery> getDeliveryList(DeliverySearchCondition deliverySearchCondition);

    boolean existOrderId(Long aLong);

    Delivery update(Delivery updateRequestDto, Long userId);

    void updateHubDeliveryStatus(DeliveryStatusUpdateRequestDto.DeliveryLogStatusDto deliveryLogStatusDto, Long userId);

    void updateCompanyDeliveryStatus(DeliveryStatusUpdateRequestDto.CompanyDeliveryLogStatusDto request, Long userId);
}
