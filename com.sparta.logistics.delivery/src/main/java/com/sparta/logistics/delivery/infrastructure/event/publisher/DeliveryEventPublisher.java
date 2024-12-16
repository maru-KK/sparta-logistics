package com.sparta.logistics.delivery.infrastructure.event.publisher;

import com.sparta.logistics.delivery.domain.event.DeliveryCreateEvent;
import com.sparta.logistics.delivery.domain.event.DomainEvent;
import com.sparta.logistics.delivery.domain.event.DomainEventEnvelop;
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
public class DeliveryEventPublisher {

    private final KafkaTemplate<String, DomainEventEnvelop<? extends DomainEvent>> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.delivery.create}")
    private String deliveryCreateTopic;

    public void publish(DomainEventEnvelop<DeliveryCreateEvent> eventEnvelop) {

        CompletableFuture<SendResult<String, DomainEventEnvelop<? extends DomainEvent>>> send =
            kafkaTemplate.send(deliveryCreateTopic, eventEnvelop);

        log.info("DeliveryEventPublisher deliveryCreate Event publish ={}", send);
    }
}
