package com.example.counter.routes;

import com.example.counter.handlers.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ServiceRoute {

    @Bean
    public RouterFunction<ServerResponse> orderRoutes(final OrderHandler orderHandler) {
        return route()
                .POST("/api/v1/orders",
                        RequestPredicates.contentType(MediaType.APPLICATION_JSON), orderHandler::placeOrder)
                .build();
    }
}
