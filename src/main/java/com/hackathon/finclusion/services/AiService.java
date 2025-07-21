package com.hackathon.finclusion.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final Client client;
    private final String model;

    // Spring AI automatically configures and injects the correct ChatClient bean
    public AiService(
            @Value("${app.ai.api-key}")
            String key,
            @Value("${app.ai.model}")
            String model

    ) {
        this.client = Client.builder().apiKey(key).build();;
        this.model = model;
    }

    public String getResponse(String message) {
        // The call method sends the prompt to Gemini and returns the response content
        GenerateContentResponse response =
                client.models.generateContent(
                        model,
                        message,
                        null);

        return response.text();
    }
}