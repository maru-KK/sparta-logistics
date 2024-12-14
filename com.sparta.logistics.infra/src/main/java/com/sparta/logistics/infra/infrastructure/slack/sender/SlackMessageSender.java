package com.sparta.logistics.infra.infrastructure.slack.sender;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SlackMessageSender {

    private final MethodsClient slackClient;

    public String sendMessage(String message, String slackUserId) {
        return sendMessage(message, List.of(slackUserId));
    }

    public String sendMessage(String message, List<String> slackUserIds) {
        try {
            for (String slackUserId : slackUserIds) {
                ChatPostMessageRequest request = createRequest(message, List.of(slackUserId));
                slackClient.chatPostMessage(request);
            }
            return "success";

        } catch (SlackApiException | IOException e) {
            log.warn("error to send message e={}", e);
            throw new RuntimeException(e);
        }
    }

    private ChatPostMessageRequest createRequest(
        String message, List<String> slackUserIds) throws SlackApiException, IOException {

        String channelId = slackClient.conversationsOpen(
            r -> r.users(slackUserIds)
        ).getChannel().getId();

        return ChatPostMessageRequest.builder()
            .channel(channelId)
            .text(message)
            .build();
    }
}
