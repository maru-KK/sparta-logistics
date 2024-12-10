package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryPersonResponse;
import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeliveryPersonService {
    private final DeliveryPersonPort deliveryPersonPort;

    public DeliveryPersonResponse createDeliveryPerson(DeliveryPersonCreateRequestDto requestDto) {
        DeliveryPerson deliveryPerson = deliveryPersonPort.createDeliveryPerson(DeliveryPerson.from(requestDto));

        return DeliveryPersonResponse.from(deliveryPerson);
    }
}
