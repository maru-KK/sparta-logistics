package com.sparta.logistics.delivery.infrastructure.kafka;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryCreationConsumer {
    private final DeliveryService deliveryService;

    @KafkaListener(topics = "create-delivery", groupId = "group_1")
    public void consumeDeliveryCreation(DeliveryCreateRequestDto dto) {
        try {
            log.info("consumeDeliveryCreation 실행");
            deliveryService.createDelivery(dto);
        } catch (Exception e) {
            log.error("consumeDeliveryCreation 실패: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
