package com.sparta.logistics.order.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class DomainEventEnvelop<T extends DomainEvent> {

    private T event;

    private UUID eventId;
    private LocalDateTime createdAt;
    private String eventType;
    private String source;

    public DomainEventEnvelop(T event, String source) {
        this.event = event;
        this.eventId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.eventType = event.getClass().getTypeName();
        this.source = source;
    }

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
