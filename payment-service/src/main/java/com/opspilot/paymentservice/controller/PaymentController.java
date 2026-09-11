package com.opspilot.paymentservice.controller;

import com.opspilot.paymentservice.dto.CreatePaymentRequest;
import com.opspilot.paymentservice.dto.PaymentResponse;
import com.opspilot.paymentservice.entity.PaymentStatus;
import com.opspilot.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {
        PaymentResponse response = paymentService.createPayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(
            @PathVariable String id,
            @RequestParam PaymentStatus status
    ) {
        PaymentResponse response =
                paymentService.updatePaymentStatus(id, status);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable String id
    ) {
        PaymentResponse response = paymentService.getPayment(id);

        return ResponseEntity.ok(response);
    }
}
