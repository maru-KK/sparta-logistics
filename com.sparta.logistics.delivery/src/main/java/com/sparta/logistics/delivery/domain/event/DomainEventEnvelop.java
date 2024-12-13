package com.sparta.logistics.delivery.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class DomainEventEnvelop<T extends DomainEvent> {

    private T event;

    private UUID eventId;
    private LocalDateTime createdAt;
    private String eventType;
    private String source;

    // for jackson serialize
    public DomainEventEnvelop() {

    }

    public T getEvent() {
        return event;
    }

    public UUID getEventId() {
        return eventId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getEventType() {
        return eventType;
    }

    public String getSource() {
        return source;
    }
}
