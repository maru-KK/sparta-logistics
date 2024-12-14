package com.sparta.logistics.infra.domain.slack;

import java.time.LocalDateTime;
import java.util.List;

public class Slack {

    private Long id;
    private String recipientId;
    private String message;
    private LocalDateTime sentAt;

    public Slack(Long id, String recipientId, String message, LocalDateTime sentAt) {
        this.id = id;
        this.recipientId = recipientId;
        this.message = message;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
