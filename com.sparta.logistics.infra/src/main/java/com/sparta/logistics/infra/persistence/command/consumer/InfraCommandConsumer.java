package com.sparta.logistics.infra.persistence.command.consumer;


import com.sparta.logistics.infra.application.service.AIService;
import com.sparta.logistics.infra.persistence.command.dto.InfraCreateMessageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InfraCommandConsumer {

    private final AIService aiService;

    @RabbitListener(queues = "${command.queue.infra}")
    public void receiveCommand(InfraCreateMessageCommand command) {
        aiService.generateResponse(command.toDomain());
    }
}