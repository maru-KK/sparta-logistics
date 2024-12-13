package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryPersonResponse;
import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryPersonService {
    private final DeliveryPersonPort deliveryPersonPort;
    private final AuthPort authPort;

    @Transactional
    public DeliveryPersonResponse createDeliveryPerson(DeliveryPersonCreateRequestDto requestDto) {
        UserDetailResponse user = authPort.findUser(requestDto.userId());
        validateUserRole(user);
//
        DeliveryPerson deliveryPerson = deliveryPersonPort.saveDeliveryPerson(DeliveryPerson.from(requestDto), user);

        return DeliveryPersonResponse.from(deliveryPerson);
    }

    public DeliveryPersonResponse getDeliveryPerson(Long deliveryPersonId) {
        DeliveryPerson deliveryPerson = deliveryPersonPort.getDeliveryPerson(deliveryPersonId);

        return DeliveryPersonResponse.from(deliveryPerson);
    }

    public Page<DeliveryPersonResponse> getDeliveryPersonList(Pageable pageable) {
        Page<DeliveryPerson> deliveryPersonList = deliveryPersonPort.getDeliveryPersonList(pageable);

        return deliveryPersonList.map(DeliveryPersonResponse::from);
    }

    @Transactional
    public DeliveryPerson getNextHubDeliveryPerson() {
        DeliveryPersonEntity nextHubDeliveryPerson = deliveryPersonPort.getNextHubDeliveryPerson();

        return DeliveryPerson.from(nextHubDeliveryPerson);
    }

    @Transactional
    public DeliveryPerson getNextCompanyDeliveryPerson(Long hubId) {
        DeliveryPersonEntity nextCompanyDeliveryPerson = deliveryPersonPort.getNextCompanyDeliveryPerson(hubId);

        return DeliveryPerson.from(nextCompanyDeliveryPerson);
    }

    private void validateUserRole(UserDetailResponse user) {
        if (!user.role().equals("DELIVERY")) {
            throw new IllegalArgumentException("배송 담당자로 지정될 수 없는 회원입니다.");
        }
    }
}
