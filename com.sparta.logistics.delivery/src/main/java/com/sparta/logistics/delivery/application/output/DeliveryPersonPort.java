package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DeliveryPersonPort {
    DeliveryPerson createDeliveryPerson(DeliveryPerson requestDto);

    DeliveryPerson getDeliveryPerson(Long deliveryPersonId);

    Page<DeliveryPerson> getDeliveryPersonList(Pageable pageable);

    DeliveryPersonEntity getNextHubDeliveryPerson();

    DeliveryPersonEntity getNextCompanyDeliveryPerson(Long hubId);
}
