package com.sparta.logistics.delivery.infrastructure.event.adapter;


import com.sparta.logistics.delivery.domain.event.DeliveryCreateEvent;
import com.sparta.logistics.delivery.domain.event.DomainEventEnvelop;
import com.sparta.logistics.delivery.infrastructure.event.publisher.DeliveryEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeliveryEventAdapter {

    private final DeliveryEventPublisher deliveryEventPublisher;

    public void publish(DeliveryCreateEvent deliveryCreateEvent) {
        DomainEventEnvelop<DeliveryCreateEvent> event =
            new DomainEventEnvelop<>(deliveryCreateEvent, "deliveryService.createDelivery");

        deliveryEventPublisher.publish(event);
    }
}
