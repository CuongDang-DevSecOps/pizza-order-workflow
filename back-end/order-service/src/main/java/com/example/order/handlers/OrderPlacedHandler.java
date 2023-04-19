package com.example.order.handlers;

import com.example.order.configs.KafkaConfig;
import com.example.order.dtos.OrderDTO;
import com.example.order.dtos.OrderInstructionDTO;
import com.example.order.dtos.OrderPlacedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record OrderPlacedHandler(KafkaTemplate<String, OrderPlacedDTO> orderPlacedKafkaTemplate,
                                 KafkaTemplate<String, OrderInstructionDTO> orderInstructionKafkaTemplate) {

    @KafkaListener(id = "order-counter", topics = KafkaConfig.PIZZA_ORDER_TOPIC)
    public void consumeOrder(OrderDTO request) {
        log.info("Consume Pizza Order {}", request);
        log.info("Dummy handle order successfully!");
        producePlacedOrder(request);
        produceOrderInstruction(request);
    }

    private void producePlacedOrder(OrderDTO request) {
        var orderPlaced = new OrderPlacedDTO(request.orderId());
        log.info("Produce Placed Order {}", orderPlaced);
        orderPlacedKafkaTemplate.send(KafkaConfig.PIZZA_RECEIVED_ORDER_TOPIC, request.orderId(), orderPlaced);
    }

    private void produceOrderInstruction(OrderDTO request) {
        var instruction = new OrderInstructionDTO(request.orderId());
        log.info("Produce Order Instruction {}", instruction);
        orderInstructionKafkaTemplate.send(KafkaConfig.PIZZA_INSTRUCTION_ORDER_TOPIC, request.orderId(), instruction);
    }
}
