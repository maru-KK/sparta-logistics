package com.sparta.logistics.order.infrastructure.event.adapter;


import com.sparta.logistics.order.domain.event.DomainEventEnvelop;
import com.sparta.logistics.order.domain.event.OrderCreateEvent;
import com.sparta.logistics.order.infrastructure.event.publisher.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventAdapter {

    private final OrderEventPublisher orderEventPublisher;

    public void publish(OrderCreateEvent orderCreateEvent) {
        DomainEventEnvelop<OrderCreateEvent> event = new DomainEventEnvelop<>(orderCreateEvent, "orderService.createOrder");
        orderEventPublisher.publish(event);
    }
}
