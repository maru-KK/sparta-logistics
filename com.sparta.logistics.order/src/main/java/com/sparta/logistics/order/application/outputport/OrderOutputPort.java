package com.sparta.logistics.order.application.outputport;

import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import com.sparta.logistics.order.domain.order.Product;

public interface OrderOutputPort {

    Order createOrder(OrderForCreate orderForCreate, Product product);

    // 단순 영속화
    void saveOrder(OrderForCreate canceledOrder, Product product);
}
