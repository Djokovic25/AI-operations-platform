package com.opspilot.paymentservice.exception;

import java.math.BigDecimal;

public class PaymentAmountMismatchException extends RuntimeException {

    public PaymentAmountMismatchException(
            BigDecimal orderAmount,
            BigDecimal paymentAmount
    ) {
        super(
                "Payment amount " + paymentAmount +
                        " does not match order amount " + orderAmount
        );
    }
}