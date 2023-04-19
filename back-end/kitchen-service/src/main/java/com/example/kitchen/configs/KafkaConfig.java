package com.example.kitchen.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    public static final String PIZZA_INSTRUCTION_ORDER_TOPIC = "pizza-instruction-order";
    public static final String PIZZA_PREPARED_ORDER_TOPIC = "pizza-prepared-order";

    @Bean
    public NewTopic pizzaPreparedOrderTopic() {
        return TopicBuilder.name(PIZZA_PREPARED_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }
}
