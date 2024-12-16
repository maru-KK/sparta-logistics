package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.dto.DeliveryStatusUpdateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.delivery.DeliveryQueryDslRepository;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.delivery.DeliveryRepository;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliverySearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryAdapter implements DeliveryPort {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryQueryDslRepository deliveryQueryDslRepository;

    @Override
    public Delivery save(DeliveryCreateRequestDto requestDto, UserDetailResponse userInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany) {
        DeliveryEntity entity = DeliveryEntity.toEntity(requestDto, userInfo, supplyCompany, consumeCompany);

        return Delivery.from(deliveryRepository.save(entity));
    }

    @Override
    public boolean existOrderId(Long orderId) {
        return deliveryRepository.existsByOrderId(orderId);
    }

    @Override
    public Delivery update(Delivery updateRequestDto, Long userId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(updateRequestDto.deliveryId()).orElseThrow(() -> new IllegalStateException("배송정보를 찾을 수 없습니다."));

        deliveryEntity.update(updateRequestDto, userId);

        return Delivery.from(deliveryEntity);
    }

    @Override
    public void updateHubDeliveryStatus(DeliveryStatusUpdateRequestDto.DeliveryLogStatusDto request, Long userId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(request.deliveryId()).orElseThrow();
        deliveryEntity.updateStatus(request.status().name(), userId);
    }

    @Override
    public void updateCompanyDeliveryStatus(DeliveryStatusUpdateRequestDto.CompanyDeliveryLogStatusDto request, Long userId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(request.deliveryId()).orElseThrow();
        deliveryEntity.updateStatus(request.status().name(), userId);
    }

    @Override
    public Delivery findOne(Long deliveryId) {
        DeliveryEntity deliveryEntity = deliveryRepository.findById(deliveryId).orElseThrow(() -> new IllegalStateException("배송정보를 찾을 수 없습니다."));

        return Delivery.from(deliveryEntity);
    }

    @Override
    public Page<Delivery> getDeliveryList(DeliverySearchCondition deliverySearchCondition) {
        Page<DeliveryEntity> deliveryEntityList = deliveryQueryDslRepository.findAll(deliverySearchCondition);

        return deliveryEntityList.map(Delivery::from);
    }
}
