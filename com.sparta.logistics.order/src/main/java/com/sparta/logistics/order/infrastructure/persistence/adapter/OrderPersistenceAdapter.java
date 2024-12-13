package com.sparta.logistics.order.infrastructure.persistence.adapter;

import com.sparta.logistics.order.application.exception.OrderNotCreateException;
import com.sparta.logistics.order.application.outputport.OrderOutputPort;
import com.sparta.logistics.order.domain.event.OrderCreateEvent;
import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.infrastructure.event.adapter.OrderEventAdapter;
import com.sparta.logistics.order.infrastructure.persistence.entity.OrderEntity;
import com.sparta.logistics.order.infrastructure.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderPersistenceAdapter implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderEventAdapter orderEventAdapter;

    @Transactional
    @Override
    public Order createOrder(OrderForCreate orderForCreate, Product product) {
        try {
            OrderEntity orderEntity = OrderEntity.from(orderForCreate, product);
            Order order = orderRepository.save(orderEntity).toDomain();

            OrderCreateEvent event = order.createEvent();
            orderEventAdapter.publish(event);

            return order;

        } catch (RuntimeException exception) {
            log.error("order-service OrderPersistenceAdapter.createOrder failure ={}", exception);
            throw new OrderNotCreateException("주문 데이터 생성에 실패했습니다.");
        }
    }

    @Override
    public void saveOrder(OrderForCreate orderForCreate, Product product) {
        OrderEntity orderEntity = OrderEntity.from(orderForCreate, product);
        Order order = orderRepository.save(orderEntity).toDomain();
    }
}
