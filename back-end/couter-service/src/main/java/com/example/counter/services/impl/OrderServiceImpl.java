package com.example.counter.services.impl;

import com.example.counter.configs.KafkaConfig;
import com.example.counter.dtos.OrderDTO;
import com.example.counter.dtos.OrderPlacingRequest;
import com.example.counter.dtos.OrderPlacingResponse;
import com.example.counter.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Override
    public Mono<OrderPlacingResponse> placeOrder(OrderPlacingRequest request) {
        var orderId = UUID.randomUUID().toString();
        log.info("Validate payload and place order successfully!");
        produceOrder(request, orderId);
        return Mono.just(new OrderPlacingResponse(orderId));
    }

    private void produceOrder(OrderPlacingRequest request, String orderId) {
        var order = new OrderDTO(orderId, request.menuItems());
        log.info("Produce Pizza Order: {}", order);
        kafkaTemplate.send(KafkaConfig.PIZZA_ORDER_TOPIC, orderId, order);
    }
}
