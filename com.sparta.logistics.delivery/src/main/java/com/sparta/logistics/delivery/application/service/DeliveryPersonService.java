package com.sparta.logistics.delivery.application.service;

import com.sparta.logistics.delivery.application.dto.DeliveryPersonResponse;
import com.sparta.logistics.delivery.application.output.DeliveryPersonPort;
import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeliveryPersonService {
    private final DeliveryPersonPort deliveryPersonPort;

    public DeliveryPersonResponse createDeliveryPerson(DeliveryPersonCreateRequestDto requestDto) {
        DeliveryPerson deliveryPerson = deliveryPersonPort.createDeliveryPerson(DeliveryPerson.from(requestDto));

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
}
