package com.sparta.logistics.infra.persistence.rest.dto;

import com.sparta.logistics.infra.domain.slack.Slack;
import com.sparta.logistics.infra.domain.slack.SlackToSendMessage;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SlackSendMessage {

    @Getter
    @NoArgsConstructor
    public static class Request {

        @NotEmpty
        private String message;

        @NotEmpty
        private List<String> recipientSlackIds;

        public SlackToSendMessage toDomain() {
            return new SlackToSendMessage(message, recipientSlackIds);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String recipientId;
        private String message;
        private LocalDateTime sentAt;

        public static Response from(Slack slack) {
            return new Response(
                slack.getId(), slack.getRecipientId(), slack.getMessage(), slack.getSentAt()
            );
        }
    }
}
