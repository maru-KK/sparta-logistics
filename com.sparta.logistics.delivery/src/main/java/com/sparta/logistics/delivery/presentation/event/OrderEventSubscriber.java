package com.sparta.logistics.delivery.presentation.event;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import com.sparta.logistics.delivery.domain.event.DomainEventEnvelop;
import com.sparta.logistics.delivery.domain.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderEventSubscriber {
    private final DeliveryService deliveryService;
    private final StringRedisTemplate redisTemplate;
    private static final String EVENT_KEY_PREFIX = "event:order:";


    @KafkaListener(
            topics = "${spring.kafka.consumer.topic.order.create}", // OrderCreateEvent
            groupId = "${spring.kafka.consumer.group-id}", // group-delivery
            containerFactory = "orderCreateEventListener"
    )
    public void consumeOrderCreateEvent(DomainEventEnvelop<OrderCreateEvent> eventEnvelop) {
        OrderCreateEvent orderCreateEvent = eventEnvelop.getEvent();
        Long orderId = orderCreateEvent.getOrderId();
        LocalDateTime createdAt = eventEnvelop.getCreatedAt();
//        createdAt = LocalDateTime.of(2024, 12, 15, 9, 30);
        String eventKey = EVENT_KEY_PREFIX + orderId;

        // 현재 이벤트가 기존 이벤트보다 과거이거나 같은 시간이면 무시
        String existingEventTimestamp = redisTemplate.opsForValue().get(eventKey);
        if (existingEventTimestamp != null) {
            LocalDateTime existingCreatedAt = LocalDateTime.parse(existingEventTimestamp);
            if (!createdAt.isAfter(existingCreatedAt)) {
                log.warn("과거 이벤트 발생 [orderId: {}, currentEventTimestamp: {}, existingEventTimestamp: {}" , orderId, createdAt, existingCreatedAt);
                return;
            }
        }

        redisTemplate.opsForValue().set(eventKey, createdAt.toString(), Duration.ofDays(1));

        // 정보를 토대로 배송 생성
        try {
            deliveryService.createDelivery(DeliveryCreateRequestDto.from(orderCreateEvent));
        } catch (Exception e) {
            log.error("Failed to process OrderCreateEvent - orderId: {}, error: {}", orderCreateEvent.getOrderId(), e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
