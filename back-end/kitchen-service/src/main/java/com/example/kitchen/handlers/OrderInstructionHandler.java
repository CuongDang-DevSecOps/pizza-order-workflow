package com.example.kitchen.handlers;

import com.example.kitchen.configs.KafkaConfig;
import com.example.kitchen.dtos.OrderInstructionDTO;
import com.example.kitchen.dtos.OrderPreparedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record OrderInstructionHandler(KafkaTemplate<String, OrderPreparedDTO> orderPreparedKafkaTemplate) {

    @KafkaListener(id = "kitten-order", topics = KafkaConfig.PIZZA_INSTRUCTION_ORDER_TOPIC)
    public void consumeOrderInstruction(OrderInstructionDTO request) {
        log.info("Consume Pizza Order Instruction {}", request);
        log.info("Dummy handle order instruction successfully!");
        producePreparedOrder(request);
    }

    private void producePreparedOrder(OrderInstructionDTO request) {
        var orderPrepared = new OrderPreparedDTO(request.orderId());
        log.info("Produce Prepared Order {}", orderPrepared);
        orderPreparedKafkaTemplate.send(KafkaConfig.PIZZA_PREPARED_ORDER_TOPIC, request.orderId(), orderPrepared);
    }
}
