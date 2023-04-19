package com.example.counter.handlers;

import com.example.counter.dtos.OrderPlacingRequest;
import com.example.counter.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record OrderHandler(OrderService orderService) {

    public Mono<ServerResponse> placeOrder(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(OrderPlacingRequest.class)
                .flatMap(this.orderService::placeOrder)
                .flatMap(orderPlacingResponse -> ServerResponse
                        .status(201)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(orderPlacingResponse)));
    }
}
