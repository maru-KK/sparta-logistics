package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DeliveryPersonPort {
    DeliveryPerson createDeliveryPerson(DeliveryPerson requestDto);

    DeliveryPerson getDeliveryPerson(Long deliveryPersonId);

    Page<DeliveryPerson> getDeliveryPersonList(Pageable pageable);
}
