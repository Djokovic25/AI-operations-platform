package com.opspilot.order_service.dto;


import com.opspilot.order_service.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {

    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private BigDecimal amount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderResponse(
            String id,
            String customerId,
            String productId,
            int quantity,
            BigDecimal amount,
            OrderStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}