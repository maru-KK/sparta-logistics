package com.sparta.logistics.delivery.infrastructure.persistence.adapter;

import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import com.sparta.logistics.delivery.infrastructure.external.hubCompany.HubCompanyPort;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.HubDeliveryPersonEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.HubDeliveryPersonRepository;
import com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryPerson.DeliveryPersonRepository;
import com.sparta.logistics.delivery.presentation.util.exception.DeliveryPersonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus.OFF_DUTY;
import static com.sparta.logistics.delivery.domain.vo.DeliveryPersonType.COMPANY_DELIVERY;
import static com.sparta.logistics.delivery.domain.vo.DeliveryPersonType.HUB_DELIVERY;


@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonAdapter implements DeliveryPersonPort {
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final HubCompanyPort hubCompanyPort;
    private final RedisTemplate<String, Integer> redisTemplate;
    private final HubDeliveryPersonRepository hubDeliveryPersonRepository;

    @Override
    public DeliveryPersonEntity getNextHubDeliveryPerson() {
        String key = "delivery:hub-to-hub:sequence";
        Integer currentSequence = Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(0);


        // sequence 기준 오름 차순, 배달 가능 상태
        List<DeliveryPersonEntity> deliveryPersonList = deliveryPersonRepository.findByHubDeliveryPerson(HUB_DELIVERY, OFF_DUTY);
        if (deliveryPersonList.isEmpty()) {
            throw new IllegalArgumentException("허브 배송 가능한 당당자가 없습니다.");
        }

        if (currentSequence >= deliveryPersonList.size()) {
            currentSequence = 0;
        }

        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonList.get(currentSequence);

        redisTemplate.opsForValue().set(key, currentSequence + 1);

        deliveryPersonEntity.setStatus(DeliveryPersonStatus.DELIVERING);

        return deliveryPersonEntity;
    }

    @Override
    public DeliveryPersonEntity getNextCompanyDeliveryPerson(Long hubId) {
        String key = "delivery:hub:" + hubId + "to-company:sequence";
        Integer currentSequence = Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(0);

        // sequence 기준 오름 차순, 배달 가능 상태
        List<DeliveryPersonEntity> deliveryPersonList = deliveryPersonRepository.findByCompanyDeliveryPerson(COMPANY_DELIVERY, OFF_DUTY, hubId);
        if (deliveryPersonList.isEmpty()) {
            throw new IllegalArgumentException("업체 배송 가능한 당당자가 없습니다.");
        }

        if (currentSequence >= deliveryPersonList.size()) {
            currentSequence = 0;
        }

        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonList.get(currentSequence);

        redisTemplate.opsForValue().set(key, currentSequence + 1);

        deliveryPersonEntity.setStatus(DeliveryPersonStatus.DELIVERING);

        return deliveryPersonEntity;
    }

    @Override
    public DeliveryPerson update(DeliveryPerson requestDto) {
        DeliveryPersonEntity entity = deliveryPersonRepository.findById(requestDto.deliveryPersonId()).orElseThrow(() -> new IllegalStateException("배송 담당자를 찾을 수 없습니다."));

        if (requestDto.hubId() != null) {
            HubDeliveryPersonEntity hubDeliveryPersonEntity = hubDeliveryPersonRepository.findByDeliveryPersonId(requestDto.deliveryPersonId());
            hubDeliveryPersonEntity.setHubId(requestDto.hubId());
        }

        entity.update(requestDto, () -> deliveryPersonRepository.findMaxSequence(requestDto.type()));

        return DeliveryPerson.from(entity);
    }

    @Override
    public DeliveryPerson saveDeliveryPerson(DeliveryPerson deliveryPerson, UserDetailResponse user) {
        validateDeliveryPersonExist(deliveryPerson);

        if (deliveryPerson.type().equals(COMPANY_DELIVERY)) {
            return saveCompanyDeliveryPerson(deliveryPerson, user);
        }

        DeliveryPersonEntity entity = saveHubDeliveryPerson(deliveryPerson, user);

        return DeliveryPerson.from(entity);
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

    private DeliveryPersonEntity saveHubDeliveryPerson(DeliveryPerson deliveryPerson, UserDetailResponse user) {
        int sequence = 1;

        List<DeliveryPersonEntity> hubDeliveryPersonList = deliveryPersonRepository.findByHubDeliveryPerson(HUB_DELIVERY, OFF_DUTY);
        if (!hubDeliveryPersonList.isEmpty()) {
            DeliveryPersonEntity deliveryPersonEntity = hubDeliveryPersonList.get(hubDeliveryPersonList.size() - 1);
            sequence = deliveryPersonEntity.getSequence() + 1;
        }

        return deliveryPersonRepository.save(DeliveryPersonEntity.of(deliveryPerson, user, sequence));
    }

    private DeliveryPerson saveCompanyDeliveryPerson(DeliveryPerson deliveryPerson, UserDetailResponse user) {
        int sequence = 1;

        List<DeliveryPersonEntity> companyDeliveryPersonList = deliveryPersonRepository.findByCompanyDeliveryPerson(COMPANY_DELIVERY, OFF_DUTY, deliveryPerson.hubId());

        if (!companyDeliveryPersonList.isEmpty()) {
            DeliveryPersonEntity deliveryPersonEntity = companyDeliveryPersonList.get(companyDeliveryPersonList.size() - 1);
            sequence = deliveryPersonEntity.getSequence() + 1;
        }

        DeliveryPersonEntity entity = deliveryPersonRepository.save(DeliveryPersonEntity.of(deliveryPerson, user, sequence));

        return saveHubDeliveryPerson(deliveryPerson, entity);
    }

    private DeliveryPerson saveHubDeliveryPerson(DeliveryPerson deliveryPerson, DeliveryPersonEntity entity) {
        if (deliveryPerson.hubId() == null) {
            throw new IllegalArgumentException("업체 배송 담당자는 허브아이디가 필수 값입니다.");
        }

        hubCompanyPort.getHubById(deliveryPerson.hubId());
        HubDeliveryPersonEntity hubDeliveryPersonEntity = hubDeliveryPersonRepository.save(new HubDeliveryPersonEntity(entity.getDeliveryPersonId(), deliveryPerson.hubId()));

        return DeliveryPerson.from(entity, hubDeliveryPersonEntity.getHubId());
    }

    private void validateDeliveryPersonExist(DeliveryPerson requestDto) {
        Optional<DeliveryPersonEntity> deliveryPerson = deliveryPersonRepository.findById(requestDto.userId());
        if (deliveryPerson.isPresent()) {
            throw new IllegalArgumentException("이미 배송 담당자로 지정 되어있습니다.");
        }
    }


}
