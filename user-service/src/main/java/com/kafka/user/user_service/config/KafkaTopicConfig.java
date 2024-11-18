package com.kafka.user.user_service.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-topic}")
    private String KAFKA_TOPIC_NAME;

    @Value("${kafka.topic.user-created}")
    private String KAFKA_TOPIC_USER_CREATED;

    @Bean
    public NewTopic createNewTopic(){
        return new NewTopic(KAFKA_TOPIC_NAME,3,(short)1);
    }

    @Bean
    public NewTopic userCreatedTopic(){
        return new NewTopic(KAFKA_TOPIC_USER_CREATED,3,(short)1);
    }
}
