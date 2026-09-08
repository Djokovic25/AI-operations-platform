package com.opspilot.order_service.repository;

import com.opspilot.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository handles the operations on entity :(inserting, reading, updating, and deleting rows inside those tables).

public interface OrderRepository extends JpaRepository<Order, String> {
}