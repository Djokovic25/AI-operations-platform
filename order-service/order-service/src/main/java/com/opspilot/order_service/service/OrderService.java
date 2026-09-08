package com.opspilot.order_service.service;


import com.opspilot.order_service.dto.CreateOrderRequest;
import com.opspilot.order_service.dto.OrderResponse;
import com.opspilot.order_service.entity.Order;
import com.opspilot.order_service.entity.OrderStatus;
import com.opspilot.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order();

        order.setId("ORDER-" + UUID.randomUUID());
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getAmount(),
                savedOrder.getStatus(),
                savedOrder.getCreatedAt()
        );
    }
}