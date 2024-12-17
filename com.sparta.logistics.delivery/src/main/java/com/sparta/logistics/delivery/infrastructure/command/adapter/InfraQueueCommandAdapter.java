package com.sparta.logistics.delivery.infrastructure.command.adapter;

import com.sparta.logistics.delivery.application.output.InfraPort;
import com.sparta.logistics.delivery.infrastructure.command.dto.InfraCreateMessageCommand;
import com.sparta.logistics.delivery.infrastructure.command.supplier.InfraQueueSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InfraQueueCommandAdapter implements InfraPort {

    private final InfraQueueSupplier infraQueueSupplier;

    @Override
    public void sendMessage(InfraCreateMessageCommand command) {
        // 재시도 로직 이라던가 예외 처리부분이라던가 로직을 추가하시면 됩니다.
        infraQueueSupplier.supply(command);
    }
}
