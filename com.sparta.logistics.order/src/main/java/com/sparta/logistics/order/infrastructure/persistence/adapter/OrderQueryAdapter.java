package com.sparta.logistics.order.infrastructure.persistence.adapter;

import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.domain.order.vo.OrderProduct;
import com.sparta.logistics.order.infrastructure.feignclient.adapter.ProductFeignClientAdapter;
import com.sparta.logistics.order.infrastructure.persistence.entity.OrderEntity;
import com.sparta.logistics.order.infrastructure.persistence.repository.OrderQueryRepository;
import com.sparta.logistics.order.infrastructure.persistence.search.OrderSearchCondition;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderQueryAdapter {

    private final OrderQueryRepository orderQueryRepository;
    private final ProductFeignClientAdapter productFeignClientAdapter;

    public Page<Order> search(OrderSearchCondition searchCondition, Long orderedBy) {
        return orderQueryRepository.findAll(searchCondition, orderedBy)
            .map(OrderEntity::toDomain);
    }

    public Optional<OrderProduct> findOne(Long orderId, Long userId) {
        try {
            OrderEntity orderEntity = validateAndGetOrder(orderId, userId);
            Product product = productFeignClientAdapter.findOne(orderEntity.getProductId());

            OrderProduct orderProduct = orderEntity.toOrderProduct(product);
            return Optional.of(orderProduct);

        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }
    }

    private OrderEntity validateAndGetOrder(Long orderId, Long userId) {
        Optional<OrderEntity> orderEntity = orderQueryRepository.findOne(orderId);
        if (orderEntity.isEmpty() || !orderEntity.get().getOrderedBy().equals(userId)) {
            throw new IllegalArgumentException("유효하지 않은 orderId");
        }
        return orderEntity.get();
    }

    public Optional<Order> findOne(Long orderId) {
        return orderQueryRepository.findOne(orderId)
            .map(OrderEntity::toDomain)
            .or(Optional::empty);
    }
}
