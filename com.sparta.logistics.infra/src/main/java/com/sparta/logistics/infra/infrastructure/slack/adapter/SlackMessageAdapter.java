package com.sparta.logistics.infra.infrastructure.slack.adapter;

import com.sparta.logistics.infra.domain.slack.Slack;
import com.sparta.logistics.infra.domain.slack.SlackToSendMessage;
import com.sparta.logistics.infra.infrastructure.persistence.adapter.SlackPersistenceAdapter;
import com.sparta.logistics.infra.infrastructure.slack.sender.SlackMessageSender;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SlackMessageAdapter {

    private final SlackMessageSender messageSender;
    private final SlackPersistenceAdapter slackPersistenceAdapter;

    @Transactional
    public List<Slack> sendMessage(SlackToSendMessage message) {
         messageSender.sendMessage(message.getMessage(), message.getRecipientId());
         return slackPersistenceAdapter.save(message);
    }
}
