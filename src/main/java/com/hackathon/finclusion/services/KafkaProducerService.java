package com.hackathon.finclusion.services;

import com.hackathon.finclusion.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true")
public class KafkaProducerService {
    private final KafkaTemplate<String, UserDto> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

    public KafkaProducerService(KafkaTemplate<String, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(UserDto user) throws ExecutionException, InterruptedException, TimeoutException {
        this.kafkaTemplate.send(USER_TOPIC, user).get(5000, TimeUnit.MILLISECONDS);
        log.info("Produced user to topic '{}': {}", USER_TOPIC, user);
    }

    public static final String USER_TOPIC = "user-topic";
}
