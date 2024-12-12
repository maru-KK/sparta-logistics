package com.sparta.logistics.order.infrastructure.persistence.adapter;

import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.infrastructure.persistence.entity.OrderEntity;
import com.sparta.logistics.order.infrastructure.persistence.repository.OrderQueryRepository;
import com.sparta.logistics.order.infrastructure.persistence.search.OrderSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderQueryAdapter {

    private final OrderQueryRepository orderQueryRepository;

    public Page<Order> search(OrderSearchCondition searchCondition, Long orderedBy) {
        return orderQueryRepository.findAll(searchCondition, orderedBy)
            .map(OrderEntity::toDomain);
    }
}
