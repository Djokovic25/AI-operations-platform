package com.opspilot.operationsmcpserver.tool;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OrderTools {

    private final RestClient restClient;

    public OrderTools(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @McpTool(
            name = "get_order",
            description = "Get an order by its order ID"
    )
    public String getOrder(
            @McpToolParam(
                    description = "The ID of the order to retrieve",
                    required = true
            )
            String orderId) {

        return restClient.get()
                .uri("/orders/{id}", orderId)
                .retrieve()
                .body(String.class);
    }
}