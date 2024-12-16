package com.sparta.logistics.order.infrastructure.event.publisher;

import com.sparta.logistics.order.domain.event.DomainEvent;
import com.sparta.logistics.order.domain.event.DomainEventEnvelop;
import com.sparta.logistics.order.domain.event.order.OrderCreateEvent;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderEventPublisher {

    private final KafkaTemplate<String, DomainEventEnvelop<? extends DomainEvent>> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.order.create}")
    private String orderCreateTopic;

    public void publish(DomainEventEnvelop<OrderCreateEvent> eventEnvelop) {

        CompletableFuture<SendResult<String, DomainEventEnvelop<? extends DomainEvent>>> send =
            kafkaTemplate.send(orderCreateTopic, eventEnvelop);

        log.info("OrderEventPublisher OrderCreate Event publish ={}", send);
    }
}
