package com.opspilot.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.opspilot.paymentservice.exception.PaymentAmountMismatchException;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(
            OrderNotFoundException ex
    ) {

        Map<String, Object> response = Map.of(
                "message", ex.getMessage(),
                "error", "ORDER_NOT_FOUND",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @ExceptionHandler(PaymentAmountMismatchException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentAmountMismatch(
            PaymentAmountMismatchException ex
    ) {

        Map<String, Object> response = Map.of(
                "message", ex.getMessage(),
                "error", "PAYMENT_AMOUNT_MISMATCH",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePaymentNotFound(
            PaymentNotFoundException ex
    ) {

        Map<String, Object> response = Map.of(
                "message", ex.getMessage(),
                "error", "PAYMENT_NOT_FOUND",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}