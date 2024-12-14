package com.sparta.logistics.infra.domain.slack;

import java.util.ArrayList;
import java.util.List;

public class SlackToSendMessage {

    private String message;
    private List<String> recipientId;

    public SlackToSendMessage(String message, List<String> recipientId) {
        this.message = message;
        this.recipientId = recipientId;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getRecipientId() {
        return new ArrayList<>(recipientId);
    }
}
