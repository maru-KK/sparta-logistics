package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
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
    public Delivery save(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany) {
        DeliveryEntity entity = DeliveryEntity.toEntity(requestDto, userInfo, supplyCompany, consumeCompany);

        return Delivery.from(deliveryRepository.save(entity));
    }

    @Override
    public boolean existOrderId(Long orderId) {
        return deliveryRepository.existsByOrderId(orderId);
    }
}
