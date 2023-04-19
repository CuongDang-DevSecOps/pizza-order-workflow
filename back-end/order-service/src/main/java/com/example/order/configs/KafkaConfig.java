package com.example.order.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {

    public static final String PIZZA_ORDER_TOPIC = "pizza-order";
    public static final String PIZZA_RECEIVED_ORDER_TOPIC = "pizza-received-order";
    public static final String PIZZA_INSTRUCTION_ORDER_TOPIC = "pizza-instruction-order";

    @Bean
    public NewTopic pizzaReceivedOrderTopic() {
        return TopicBuilder.name(PIZZA_RECEIVED_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic pizzaInstructionOrderTopic() {
        return TopicBuilder.name(PIZZA_INSTRUCTION_ORDER_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public RecordMessageConverter recordMessageConverter() {
        return new StringJsonMessageConverter();
    }
}
