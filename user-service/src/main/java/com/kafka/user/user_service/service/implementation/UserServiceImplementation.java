package com.kafka.user.user_service.service.implementation;

import com.kafka.event.UserCreatedEvent;
import com.kafka.user.user_service.dto.UserDto;
import com.kafka.user.user_service.entities.UserEntity;

import com.kafka.user.user_service.repository.UserRepository;
import com.kafka.user.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {

    @Value("${kafka.topic.user-created}")
    private String KAFKA_TOPIC_USER_CREATED;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long , UserCreatedEvent> userCreatedEntryKafkaTemplate;

    @Override
    public void createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);
        UserCreatedEvent userCreatedEntry = modelMapper.map(savedUser, UserCreatedEvent.class);
        userCreatedEntryKafkaTemplate.send(KAFKA_TOPIC_USER_CREATED,userCreatedEntry.getId(),userCreatedEntry);
    }
}
