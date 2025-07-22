package com.hackathon.finclusion.listeners;

import com.hackathon.finclusion.model.UserDto;
import com.hackathon.finclusion.services.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true")
public class UserListener {
    private static final Logger log = LoggerFactory.getLogger(UserListener.class);

    @KafkaListener(topics = KafkaProducerService.USER_TOPIC)
    public void consumeAiInteractionEvent(UserDto user) {
        log.info("Consumed user: {}", user);
    }
}
