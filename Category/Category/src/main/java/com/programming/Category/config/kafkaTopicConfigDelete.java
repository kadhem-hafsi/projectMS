package com.programming.Category.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class kafkaTopicConfigDelete {
    private String topicnameDelete="TopicDeleCategory";
    @Bean
    public NewTopic topicDelte(){
        return TopicBuilder.name(topicnameDelete).build();
    }
}
