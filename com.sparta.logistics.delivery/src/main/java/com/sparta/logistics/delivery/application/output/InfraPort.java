package com.sparta.logistics.delivery.application.output;

import com.sparta.logistics.delivery.infrastructure.command.dto.InfraCreateMessageCommand;

public interface InfraPort {

    void sendMessage(InfraCreateMessageCommand command);
}
