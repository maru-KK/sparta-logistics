package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hub.HubPort;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonAdapter implements DeliveryPersonPort {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final AuthPort authPort;
    private final HubPort hubPort;

    @Override
    @Transactional
    public DeliveryPerson createDeliveryPerson(DeliveryPerson requestDto) {
        validateUserRole(requestDto);

        hubPort.getHubById(requestDto.hubId());

        DeliveryPersonEntity entity = deliveryPersonRepository.save(DeliveryPersonEntity.from(requestDto));

        return DeliveryPerson.from(entity);
    }

    private void validateUserRole(DeliveryPerson requestDto) {
        UserDetailResponse user = authPort.findUser(requestDto.userId());

        if (!user.role().equals("MASTER")) {
            throw new IllegalArgumentException("배송 담당자 생성 권한이 없습니다.");
        }
    }
}
