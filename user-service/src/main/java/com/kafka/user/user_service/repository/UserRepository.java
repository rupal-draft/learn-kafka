package com.kafka.user.user_service.repository;

import com.kafka.user.user_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
