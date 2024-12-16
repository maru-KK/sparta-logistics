package com.sparta.logistics.order.presentation.event.subscriber;

import com.sparta.logistics.order.application.service.OrderService;
import com.sparta.logistics.order.domain.event.DomainEventEnvelop;
import com.sparta.logistics.order.domain.event.delivery.DeliveryCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeliveryEventSubscriber {

    private final OrderService orderService;

    @KafkaListener(
        topics = "${spring.kafka.consumer.topic.delivery.create}",
        groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "deliveryCreateEventListener"
    )
    public void consumeDeliveryCreateEvent(DomainEventEnvelop<DeliveryCreateEvent> eventEnvelop) {
        DeliveryCreateEvent event = eventEnvelop.getEvent();
        orderService.updateStatusInDeliver(event.getOrderId());
    }
}
