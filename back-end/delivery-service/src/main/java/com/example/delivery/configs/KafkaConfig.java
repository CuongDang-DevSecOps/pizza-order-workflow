package com.example.delivery.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    public static final String PIZZA_PREPARED_ORDER_TOPIC = "pizza-prepared-order";
    public static final String PIZZA_COLLECTED_ORDER_TOPIC = "pizza-collected-order";
    public static final String PIZZA_DELIVERING_ORDER_TOPIC = "pizza-delivering-order";
    public static final String PIZZA_DELIVERED_ORDER_TOPIC = "pizza-delivered-order";

    @Bean
    public NewTopic pizzaCollectedOrderTopic() {
        return TopicBuilder.name(PIZZA_COLLECTED_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic pizzaDeliveringOrderTopic() {
        return TopicBuilder.name(PIZZA_DELIVERING_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic pizzaDeliveredOrderTopic() {
        return TopicBuilder.name(PIZZA_DELIVERED_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }
}
