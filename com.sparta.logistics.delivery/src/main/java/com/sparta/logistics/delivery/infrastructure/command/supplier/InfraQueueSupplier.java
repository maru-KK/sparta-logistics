package com.sparta.logistics.delivery.infrastructure.command.supplier;

import com.sparta.logistics.delivery.infrastructure.command.dto.InfraCreateMessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InfraQueueSupplier {

    private final RabbitTemplate rabbitTemplate;

    @Value("${command.queue.infra}")
    private String infraQueue;

    public void supply(InfraCreateMessageCommand command) {
        rabbitTemplate.convertAndSend(infraQueue, command);
    }
}
