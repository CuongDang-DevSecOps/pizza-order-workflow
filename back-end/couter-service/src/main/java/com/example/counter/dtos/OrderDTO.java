package com.example.counter.dtos;

import java.util.Set;

public record OrderDTO(String orderId, Set<MenuItemDTO> items) {
}
