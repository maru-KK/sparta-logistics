package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.domain.DeliveryPerson;


public interface DeliveryPersonPort {
    DeliveryPerson createDeliveryPerson(DeliveryPerson requestDto);
}
