package com.example.delivery.handlers;

import com.example.delivery.configs.KafkaConfig;
import com.example.delivery.dtos.OrderCollectedDTO;
import com.example.delivery.dtos.OrderDeliveredDTO;
import com.example.delivery.dtos.OrderDeliveringDTO;
import com.example.delivery.dtos.OrderPreparedDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record OrderPreparedHandler(KafkaTemplate<String, OrderCollectedDTO> orderCollectedKafkaTemplate,
                                   KafkaTemplate<String, OrderDeliveringDTO> orderDeliveringKafkaTemplate,
                                   KafkaTemplate<String, OrderDeliveredDTO> orderDeliveredKafkaTemplate) {

    @KafkaListener(id = "kitten-order", topics = KafkaConfig.PIZZA_PREPARED_ORDER_TOPIC)
    public void consumeOrderInstruction(OrderPreparedDTO request) {
        log.info("Consume Pizza Prepared Order {}", request);
        log.info("Dummy handle order prepared successfully!");
        produceCollectedOrder(request);
        produceOrderDelivering(request);
        produceDeliveredOrder(request);
    }

    private void produceCollectedOrder(OrderPreparedDTO request) {
        var orderCollected = new OrderCollectedDTO(request.orderId());
        log.info("Produce Collected Order {}", orderCollected);
        orderCollectedKafkaTemplate.send(KafkaConfig.PIZZA_COLLECTED_ORDER_TOPIC, request.orderId(), orderCollected);
    }

    private void produceOrderDelivering(OrderPreparedDTO request) {
        var orderDelivering = new OrderDeliveringDTO(request.orderId());
        log.info("Produce Order Delivering {}", orderDelivering);
        orderDeliveringKafkaTemplate.send(KafkaConfig.PIZZA_DELIVERING_ORDER_TOPIC, request.orderId(), orderDelivering);
    }

    private void produceDeliveredOrder(OrderPreparedDTO request) {
        var orderDelivered = new OrderDeliveredDTO(request.orderId());
        log.info("Produce Delivered Order {}", orderDelivered);
        orderDeliveredKafkaTemplate.send(KafkaConfig.PIZZA_DELIVERED_ORDER_TOPIC, request.orderId(), orderDelivered);
    }

}
