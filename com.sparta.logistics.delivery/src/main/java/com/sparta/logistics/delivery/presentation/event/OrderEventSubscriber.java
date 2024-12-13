package com.sparta.logistics.delivery.presentation.event;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.domain.event.DomainEventEnvelop;
import com.sparta.logistics.delivery.domain.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderEventSubscriber {
    private final DeliveryService deliveryService;

    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.order.create}", // OrderCreateEvent
            groupId = "${spring.kafka.consumer.group-id}", // group-delivery
            containerFactory = "orderCreateEventListener"
    )
    public void consumeOrderCreateEvent(DomainEventEnvelop<OrderCreateEvent> eventEnvelop) {
        OrderCreateEvent orderCreateEvent = eventEnvelop.getEvent();

        // 정보를 토대로 배송 생성
        try {
            deliveryService.createDelivery(DeliveryCreateRequestDto.from(orderCreateEvent));
        } catch (Exception e) {
            log.error("Failed to process OrderCreateEvent - orderId: {}, error: {}", orderCreateEvent.getOrderId(), e.getMessage(), e);
            throw new RuntimeException(e);
        }


        // 배송 생성 이벤트 발행 .....


    }
}
