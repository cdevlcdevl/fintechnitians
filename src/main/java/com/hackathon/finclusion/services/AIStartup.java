package com.hackathon.finclusion.services;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@ConditionalOnProperty(name = "app.ai.startup-test-enabled", havingValue = "true")
public class AIStartup {
    private final AiService aiService;
    public AIStartup(
            AiService aiService
    ) {
        this.aiService = aiService;
    }

    @PostConstruct
    public void init() {
        System.out.println(aiService.getResponse("make a joke about fat man"));
    }
}
