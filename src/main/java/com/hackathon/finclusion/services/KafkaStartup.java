package com.hackathon.finclusion.services;

import com.hackathon.finclusion.listeners.UserListener;
import com.hackathon.finclusion.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Component
@Profile("!test")
@ConditionalOnProperty(name = "app.kafka.startup-test-enabled", havingValue = "true")
public class KafkaStartup {
    private final KafkaProducerService service;
    private static final Logger log = LoggerFactory.getLogger(KafkaStartup.class);
    public KafkaStartup(
            KafkaProducerService service
    ) {
        this.service = service;
    }

    @PostConstruct
    public void init() throws ExecutionException, InterruptedException, TimeoutException {
        service.sendUser(new UserDto("cdevl", "cdevl@gmail.com"));
        log.info("User info sent to kafka on startup");
    }
}
