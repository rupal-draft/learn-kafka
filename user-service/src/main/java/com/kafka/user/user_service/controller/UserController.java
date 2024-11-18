package com.kafka.user.user_service.controller;

import com.kafka.user.user_service.dto.UserDto;
import com.kafka.user.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Value("${kafka.topic.user-topic}")
    private String KAFKA_TOPIC_NAME;
    private final KafkaTemplate<String , String> kafkaTemplate;
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message){
        log.info("Sending message!!");
        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(KAFKA_TOPIC_NAME,""+i%2,message+":"+i);
        }
        log.info("Message queued!");
        return ResponseEntity.ok("Message queued");
    }
}
