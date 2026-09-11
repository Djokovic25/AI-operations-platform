package com.opspilot.paymentservice.service;

import com.opspilot.paymentservice.dto.CreatePaymentRequest;
import com.opspilot.paymentservice.dto.OrderResponse;
import com.opspilot.paymentservice.dto.PaymentResponse;
import com.opspilot.paymentservice.entity.Payment;
import com.opspilot.paymentservice.entity.PaymentStatus;
import com.opspilot.paymentservice.exception.PaymentAmountMismatchException;
import com.opspilot.paymentservice.exception.PaymentNotFoundException;
import com.opspilot.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.opspilot.paymentservice.exception.OrderNotFoundException;
import org.springframework.web.client.HttpClientErrorException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestClient restClient;
    private final String orderServiceUrl;

    public PaymentService(
            PaymentRepository paymentRepository,
            RestClient restClient,
            @Value("${order.service.url}") String orderServiceUrl
    ) {
        this.paymentRepository = paymentRepository;
        this.restClient = restClient;
        this.orderServiceUrl = orderServiceUrl;
    }

    public PaymentResponse createPayment(CreatePaymentRequest request) {

        // Verify that the order exists in Order Service
        OrderResponse order;

        try {
            order = restClient.get()
                    .uri(orderServiceUrl + "/orders/" + request.getOrderId())
                    .retrieve()
                    .body(OrderResponse.class);

        } catch (HttpClientErrorException.NotFound ex) {
            throw new OrderNotFoundException(request.getOrderId());
        }
        if (order == null || order.getAmount() == null) {
            throw new RuntimeException("Order amount is missing");
        }

        if (order.getAmount().compareTo(request.getAmount()) != 0) {
            throw new PaymentAmountMismatchException(
                    order.getAmount(),
                    request.getAmount()
            );
        }

        Payment payment = new Payment();

        payment.setId("PAYMENT-" + UUID.randomUUID());
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return new PaymentResponse(
                savedPayment.getId(),
                savedPayment.getOrderId(),
                savedPayment.getAmount(),
                savedPayment.getStatus(),
                savedPayment.getCreatedAt()
        );
    }

    public PaymentResponse updatePaymentStatus(
            String paymentId,
            PaymentStatus status
    ) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found: " + paymentId)
                );

        payment.setStatus(status);

        Payment savedPayment = paymentRepository.save(payment);

        return new PaymentResponse(
                savedPayment.getId(),
                savedPayment.getOrderId(),
                savedPayment.getAmount(),
                savedPayment.getStatus(),
                savedPayment.getCreatedAt()
        );
    }
    public PaymentResponse getPayment(String paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new PaymentNotFoundException(paymentId)
                );

        return new PaymentResponse(
                payment.getId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }
}

