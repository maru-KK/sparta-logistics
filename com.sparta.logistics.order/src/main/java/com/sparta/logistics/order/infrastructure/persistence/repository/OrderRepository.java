package com.sparta.logistics.order.infrastructure.persistence.repository;

import com.sparta.logistics.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
