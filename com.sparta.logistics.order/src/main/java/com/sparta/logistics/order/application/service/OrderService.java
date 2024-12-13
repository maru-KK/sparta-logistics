package com.sparta.logistics.order.application.service;

import com.sparta.logistics.order.application.exception.OrderNotCreateException;
import com.sparta.logistics.order.application.outputport.OrderOutputPort;
import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import com.sparta.logistics.order.domain.order.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderOutputPort orderOutputPort;
    private final ProductService productService;

    public Order createOrder(OrderForCreate orderForCreate) {
        Product product = productService.getOrderableProduct(
            orderForCreate.getProductId(), orderForCreate.getOrderedQuantity()
        );
        return tryToCreate(orderForCreate, product);
    }

    private Order tryToCreate(OrderForCreate order, Product product) {
        try {
            productService.decreaseProductQuantity(order.getProductId(), order.getOrderedQuantity(), order.getOrderedBy());
            return orderOutputPort.createOrder(order, product);

        } catch (OrderNotCreateException exception) {
            rollbackTryToCreate(order, product);
            throw exception;
        }
    }

    private void rollbackTryToCreate(OrderForCreate order, Product product) {
        // increase product Quantity & persistence order to CANCLED
        productService.increaseProductQuantity(order.getProductId(), order.getOrderedQuantity(), order.getOrderedBy());
        OrderForCreate canceledOrder = order.cancel();
        orderOutputPort.saveOrder(canceledOrder, product);
    }
}
