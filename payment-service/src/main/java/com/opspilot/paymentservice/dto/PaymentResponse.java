package com.opspilot.paymentservice.dto;

import com.opspilot.paymentservice.entity.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private String id;
    private String orderId;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public PaymentResponse(
            String id,
            String orderId,
            BigDecimal amount,
            PaymentStatus status,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}