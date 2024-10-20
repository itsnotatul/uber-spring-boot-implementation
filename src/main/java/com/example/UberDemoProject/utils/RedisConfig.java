package com.example.UberDemoProject.utils;

import com.example.UberDemoProject.model.Taxi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Taxi> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Taxi> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use Jackson2JsonRedisSerializer for serialization
        Jackson2JsonRedisSerializer<Taxi> serializer = new Jackson2JsonRedisSerializer<>(Taxi.class);
        template.setDefaultSerializer(serializer);

        return template;
    }
}
