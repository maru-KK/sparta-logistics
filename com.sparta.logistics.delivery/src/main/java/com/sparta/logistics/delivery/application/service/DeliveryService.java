package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.output.DeliveryLogPort;
import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.application.output.DeliveryPort;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.infrastructure.external.hub.HubCompanyPort;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryPort deliveryPort;
    private final DeliveryPersonPort deliveryPersonPort;
    private final DeliveryLogPort deliveryLogPort;
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final AuthPort authPort;
    private final HubCompanyPort hubCompanyPort;
    private final DeliveryPersonService deliveryPersonService;

    public Delivery createDelivery(DeliveryCreateRequestDto requestDto) {
//        CompanyResponse supplyCompany = hubCompanyPort.getCompanyById(requestDto.supplyCompanyId());
//        CompanyResponse consumeCompany = hubCompanyPort.getCompanyById(requestDto.consumeCompanyId());

//        UserDetailResponse userInfo = authPort.findUser(1L);

        // 출발 도착 허브 아이디 요청해서 허브루트 정보 받기
//        HubInfoResponseDto hubInfoResponseDto = hubClient.findByHubRoute(supplyCompanyInfo.hubId(), consumeCompanyInfo.hubId());

        // 허브 배송 담당자 배정
        DeliveryPerson nextHubDeliveryPerson = deliveryPersonService.getNextHubDeliveryPerson();
        // 업체 배송 담당자 지정
        DeliveryPerson nextCompanyDeliveryPerson = deliveryPersonService.getNextCompanyDeliveryPerson(1L);

        Delivery delivery = deliveryPort.save(requestDto, userInfo);


        deliveryLogPort.save(delivery, hubRouteInfo, nextHubDeliveryPerson);

//                배송 경로 기록 (p_delivery_log) 저장
//        deliveryLogPort.
        return null;
    }
}
