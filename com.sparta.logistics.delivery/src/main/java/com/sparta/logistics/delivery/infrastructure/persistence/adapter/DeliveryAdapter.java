package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hub.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryAdapter implements DeliveryPort {
    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery save(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo) {
        CompanyResponse supplyCompany = new CompanyResponse(1L, 3L, "address");
        CompanyResponse consumeCompany = new CompanyResponse(17L, 2L, "address");

        DeliveryEntity entity = DeliveryEntity.toEntity(requestDto, userInfo, supplyCompany, consumeCompany);

        return Delivery.from(deliveryRepository.save(entity));
    }
}
