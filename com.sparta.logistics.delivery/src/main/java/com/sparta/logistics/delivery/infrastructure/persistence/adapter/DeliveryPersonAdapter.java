package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.external.auth.AuthPort;
import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.infrastructure.external.hub.HubPort;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.DeliveryPersonRepository;
import com.sparta.logistics.delivery.presentation.util.exception.DeliveryPersonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        UserDetailResponse user = authPort.findUser(requestDto.userId());

        validateUserRole(user);

        validateDeliveryPersonExist(requestDto);

        DeliveryPersonEntity entity = deliveryPersonRepository.save(DeliveryPersonEntity.from(requestDto, user));
        if (entity.getType().equals(COMPANY_DELIVERY)) {
            return saveHubDeliveryPerson(requestDto, entity);
        }

        return DeliveryPerson.from(entity);
    }

    private DeliveryPerson saveHubDeliveryPerson(DeliveryPerson requestDto, DeliveryPersonEntity entity) {
        if (requestDto.hubId() == null) {
            throw new IllegalArgumentException("업체 배송 담당자는 허브아이디가 필수 값입니다.");
        }

        hubCompanyPort.getHubById(requestDto.hubId());
        HubDeliveryPersonEntity hubDeliveryPersonEntity = hubDeliveryPersonRepository.save(new HubDeliveryPersonEntity(entity.getDeliveryPersonId(), requestDto.hubId()));

        return DeliveryPerson.from(entity, hubDeliveryPersonEntity.getHubId());
    }

    private void validateDeliveryPersonExist(DeliveryPerson requestDto) {
        Optional<DeliveryPersonEntity> deliveryPerson = deliveryPersonRepository.findById(requestDto.userId());
        if (deliveryPerson.isPresent()) {
            throw new IllegalArgumentException("이미 배송 담당자로 지정 되어있습니다.");
        }
    }

    @Override
    public DeliveryPerson getDeliveryPerson(Long deliveryPersonId) {
        DeliveryPersonEntity entity = deliveryPersonRepository.findById(deliveryPersonId).orElseThrow(() -> new DeliveryPersonNotFoundException("배송 담당자를 찾을 수 없습니다."));

        return DeliveryPerson.from(entity);
    }

    @Override
    public Page<DeliveryPerson> getDeliveryPersonList(Pageable pageable) {

        return deliveryPersonRepository.findAllByIsDeletedFalse(pageable).map(DeliveryPerson::from);
    }

    private void validateUserRole(UserDetailResponse user) {
        if (!user.role().equals("DELIVERY")) {
            throw new IllegalArgumentException("배송 담당자로 지정될 수 없는 회원입니다.");
        }
    }
}
