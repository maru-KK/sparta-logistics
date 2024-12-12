package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryLogPort;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hub.HubCompanyPort;
import com.sparta.logistics.delivery.infrastructure.external.hub.dto.HubRouteResponseDto;
import com.sparta.logistics.delivery.infrastructure.external.hubRoute.HubRoutePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryPort deliveryPort;
    private final DeliveryLogPort deliveryLogPort;
    private final AuthPort authPort;
    private final HubCompanyPort hubCompanyPort;
    private final DeliveryPersonService deliveryPersonService;
    private final HubRoutePort hubRoutePort;

    @Transactional
    public Delivery createDelivery(DeliveryCreateRequestDto requestDto) {
//        CompanyResponse supplyCompany = hubCompanyPort.getCompanyById(requestDto.supplyCompanyId());
//        CompanyResponse consumeCompany = hubCompanyPort.getCompanyById(requestDto.consumeCompanyId());

        // 허브 배송 담당자 배정
        DeliveryPerson nextHubDeliveryPerson = deliveryPersonService.getNextHubDeliveryPerson();
        // 업체 배송 담당자 지정
        DeliveryPerson nextCompanyDeliveryPerson = deliveryPersonService.getNextCompanyDeliveryPerson(1L);

        UserDetailResponse userInfo = authPort.findUser(1L);
        Delivery delivery = deliveryPort.save(requestDto, userInfo);

        HubRouteResponseDto hubRouteInfo = hubRoutePort.getRouteByOriginAndDestination(1L, 17L);
        deliveryLogPort.save(delivery, hubRouteInfo, nextHubDeliveryPerson);

        return delivery;
    }
}
