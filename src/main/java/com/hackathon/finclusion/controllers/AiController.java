package com.hackathon.finclusion.controllers;

import com.hackathon.finclusion.services.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai") // Establishes a base path for all endpoints in this controller
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/prompt")
    public ResponseEntity<String> getAiResponse(
            @RequestParam(defaultValue = "Tell me a fun fact about programming") String message
    ) {
        if (message == null || message.isBlank()) {
            return ResponseEntity.badRequest().body("The 'message' parameter cannot be empty.");
        }
        String response = aiService.getResponse(message);
        return ResponseEntity.ok(response);
    }
}