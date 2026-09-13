package com.opspilot.operationsmcpserver.tool;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentTools {

    private final RestClient restClient;

    public PaymentTools(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8081")
                .build();
    }

    @McpTool(
            name = "get_payment",
            description = "Get a payment by its payment ID"
    )
    public String getPayment(
            @McpToolParam(
                    description = "The ID of the payment to retrieve",
                    required = true
            )
            String paymentId) {

        return restClient.get()
                .uri("/payments/{id}", paymentId)
                .retrieve()
                .body(String.class);
    }
}