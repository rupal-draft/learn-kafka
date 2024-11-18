package com.kafka.notification.notification_service.consumers;

import com.kafka.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-topic")
    public void receiveMessage1(String message){
        log.info(message);
    }

    @KafkaListener(topics = "user-created-topic")
    public void receiveCreatedUser(UserCreatedEvent userCreatedEvent){
        log.info(String.valueOf(userCreatedEvent));
    }
}
