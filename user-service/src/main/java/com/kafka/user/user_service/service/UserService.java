package com.kafka.user.user_service.service;

import com.kafka.user.user_service.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
}
