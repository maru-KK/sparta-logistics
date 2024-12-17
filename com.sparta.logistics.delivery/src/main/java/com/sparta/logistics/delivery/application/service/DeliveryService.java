package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.dto.DeliveryStatusUpdateRequestDto;
import com.sparta.logistics.delivery.application.output.CompanyDeliveryRoutePort;
import com.sparta.logistics.delivery.application.output.DeliveryLogPort;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.event.adapter.DeliveryEventAdapter;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.HubCompanyPort;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.CompanyResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.dto.HubRouteResponseDto;
import com.sparta.logistics.delivery.infrastructure.external.hubRoute.HubRoutePort;
import com.sparta.logistics.delivery.infrastructure.external.product.ProductDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.product.ProductPort;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliverySearchCondition;
import com.sparta.logistics.delivery.presentation.dto.DeliveryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {
    private final DeliveryPort deliveryPort;
    private final DeliveryLogPort deliveryLogPort;
    private final CompanyDeliveryRoutePort companyDeliveryRoutePort;
    private final AuthPort authPort;
    private final HubCompanyPort hubCompanyPort;
    private final DeliveryPersonService deliveryPersonService;
    private final HubRoutePort hubRoutePort;
    private final InfraService infraService;
    private final ProductPort productPort;
    private final DeliveryEventAdapter deliveryEventAdapter;

    @Transactional
    public Delivery createDelivery(DeliveryCreateRequestDto requestDto) {
        boolean result = deliveryPort.existOrderId(requestDto.orderId());
        if (result) {
            throw new IllegalArgumentException("이미 존재하는 배송입니다.");
        }

        CompanyResponse supplyCompany = hubCompanyPort.getCompanyById(requestDto.supplyCompanyId());
        CompanyResponse consumeCompany = hubCompanyPort.getCompanyById(requestDto.consumeCompanyId());

        // 허브 배송 담당자 배정
        DeliveryPerson nextHubDeliveryPerson = deliveryPersonService.getNextHubDeliveryPerson();
        // 업체 배송 담당자 지정
        DeliveryPerson nextCompanyDeliveryPerson = deliveryPersonService.getNextCompanyDeliveryPerson(consumeCompany.hub().getHubId());

        // 수령인 정보 조회
        UserDetailResponse orderByUser = authPort.findUser(requestDto.orderedBy());

        Delivery delivery = deliveryPort.save(requestDto, orderByUser, supplyCompany, consumeCompany);

        // 허브 루트 정보 조회
        HubRouteResponseDto hubRouteInfo = hubRoutePort.getRouteByOriginAndDestination(supplyCompany.hub().getHubId(), consumeCompany.hub().getHubId());
        deliveryLogPort.save(delivery, hubRouteInfo, nextHubDeliveryPerson);

        companyDeliveryRoutePort.save(delivery.deliveryId(), nextCompanyDeliveryPerson.deliveryPersonId(), consumeCompany);

        deliveryEventAdapter.publish(delivery.createEvent());

        UserDetailResponse originHubUserInfo = authPort.findUser(supplyCompany.hub().getUserId());
        sendAI(requestDto, orderByUser, nextCompanyDeliveryPerson, originHubUserInfo, supplyCompany, consumeCompany);

        return delivery;
    }

    private void sendAI(DeliveryCreateRequestDto requestDto, UserDetailResponse orderByUser, DeliveryPerson nextCompanyDeliveryPerson, UserDetailResponse originHubUserInfo, CompanyResponse supplyCompany, CompanyResponse consumeCompany) {
        UserDetailResponse deliveryPersonInfo = authPort.findUser(nextCompanyDeliveryPerson.userId());
        ProductDetailResponse productDetailResponse = productPort.findOne(requestDto.productId());

        infraService.sendMessage(requestDto.orderId(), orderByUser, originHubUserInfo, productDetailResponse, requestDto.quantity(), requestDto.request(), supplyCompany, consumeCompany, deliveryPersonInfo);
    }

    public DeliveryResponseDto getDelivery(Long deliveryId) {
        Delivery delivery = deliveryPort.findOne(deliveryId);

        return DeliveryResponseDto.from(delivery);
    }

    public Page<DeliveryResponseDto> getDeliveryList(DeliverySearchCondition deliverySearchCondition) {
        Page<Delivery> deliveryList = deliveryPort.getDeliveryList(deliverySearchCondition);

        return deliveryList.map(DeliveryResponseDto::from);

    }

    @Transactional
    public Delivery updateDelivery(Delivery delivery, Long userId) {
        delivery.updateValidate();

        return deliveryPort.update(delivery, userId);
    }

    @Transactional
    public void updateHubDeliveryStatus(DeliveryStatusUpdateRequestDto.DeliveryLogStatusDto requestDto, Long userId) {
        deliveryLogPort.update(requestDto, userId);
        deliveryPort.updateHubDeliveryStatus(requestDto, userId);
    }

    @Transactional
    public void updateCompanyDeliveryStatus(DeliveryStatusUpdateRequestDto.CompanyDeliveryLogStatusDto requestDto, Long userId) {
        companyDeliveryRoutePort.update(requestDto, userId);
        deliveryPort.updateCompanyDeliveryStatus(requestDto, userId);
    }
}
