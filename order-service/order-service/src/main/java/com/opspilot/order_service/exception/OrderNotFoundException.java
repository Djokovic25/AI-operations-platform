package com.opspilot.order_service.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String id) {
        super("Order not found: " + id);
    }
}