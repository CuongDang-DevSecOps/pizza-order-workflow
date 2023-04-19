package com.example.counter.handlers;

import com.example.counter.configs.KafkaConfig;
import com.example.counter.dtos.OrderPlacedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record OrderResultHandler() {

    @KafkaListener(id = "counter-order", topics = KafkaConfig.PIZZA_RECEIVED_ORDER_TOPIC)
    public void consumeOrder(OrderPlacedDTO request) {
        log.info("Consume Pizza Order Result {}", request);
    }
}
