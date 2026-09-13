package com.opspilot.operationsaiagent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ToolCallbackProvider mcpTools;

    public ChatService(
            ChatClient.Builder chatClientBuilder,
            ToolCallbackProvider mcpTools
    ) {
        this.chatClient = chatClientBuilder.build();
        this.mcpTools = mcpTools;
    }

    public String chat(String message) {

        return chatClient.prompt()
                .user(message)
                .tools(mcpTools)
                .call()
                .content();
    }
}