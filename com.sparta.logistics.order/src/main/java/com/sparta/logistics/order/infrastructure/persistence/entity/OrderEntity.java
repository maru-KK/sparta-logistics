package com.sparta.logistics.order.infrastructure.persistence.entity;

import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.infrastructure.persistence.entity.vo.OrderEntityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "p_order")
@Entity(name = "order")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long orderedBy;
    private Long consumeCompanyId;
    private Long supplyCompanyId;

    private Long productId;
    private Integer quantity;

    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;
    private OrderEntityStatus status;

    private OrderEntity(
        Long orderedBy, Long consumeCompanyId,
        Long supplyCompanyId, Long productId, Integer quantity,
        String requestMessage, LocalDateTime deliveryLimitedAt, OrderEntityStatus status
    ) {
        super.createdFrom(orderedBy);
        this.orderedBy = orderedBy;
        this.consumeCompanyId = consumeCompanyId;
        this.supplyCompanyId = supplyCompanyId;
        this.productId = productId;
        this.quantity = quantity;
        this.requestMessage = requestMessage;
        this.deliveryLimitedAt = deliveryLimitedAt;
        this.status = status;
    }

    public static OrderEntity from(OrderForCreate order) {
        Product orderedProduct = order.getOrderedProduct();
        return new OrderEntity(
            order.getOrderedUser(),
            order.getOrderedCompany(),
            orderedProduct.getSupplyCompany(),
            orderedProduct.getId(),
            orderedProduct.getQuantity(),
            order.getRequestMessage(),
            order.getDeliveryLimitedAt(),
            OrderEntityStatus.valueOf(order.getStatus().name())
        );
    }

    public Order toDomain() {
        return new Order(
            orderId, orderedBy, consumeCompanyId, supplyCompanyId,
            productId, quantity, requestMessage, deliveryLimitedAt, status.toDomain()
        );
    }
}
