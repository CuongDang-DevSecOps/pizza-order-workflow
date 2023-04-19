package com.example.counter.services;

import com.example.counter.dtos.OrderPlacingRequest;
import com.example.counter.dtos.OrderPlacingResponse;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderPlacingResponse> placeOrder(OrderPlacingRequest request);
}
