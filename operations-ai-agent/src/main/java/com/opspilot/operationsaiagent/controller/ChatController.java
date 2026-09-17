package com.opspilot.operationsaiagent.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.opspilot.operationsaiagent.service.ChatService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String response = chatService.chat(request.message());

        return new ChatResponse(response);
    }

    public record ChatRequest(String message) {
    }

    public record ChatResponse(String response) {
    }
}